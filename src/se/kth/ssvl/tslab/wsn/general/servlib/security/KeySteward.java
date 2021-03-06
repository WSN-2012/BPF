/*
 * Copyright 2012 KTH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package se.kth.ssvl.tslab.wsn.general.servlib.security;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.SDNV;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleDaemon;
import se.kth.ssvl.tslab.wsn.general.servlib.naming.endpoint.EndpointID;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.IByteBuffer;
import ext.org.bouncycastle.cms.CMSEnvelopedData;
import ext.org.bouncycastle.cms.CMSEnvelopedDataGenerator1;
import ext.org.bouncycastle.cms.CMSProcessable;
import ext.org.bouncycastle.cms.CMSProcessableByteArray;
import ext.org.bouncycastle.cms.RecipientId;
import ext.org.bouncycastle.cms.RecipientInformation;
import ext.org.bouncycastle.cms.RecipientInformationStore;

/**
 * This class provides support for public key cryptography. It currently
 * contains methods to encrypt and decrypt the symmetric key.
 */
public class KeySteward {
	private static String TAG = "KeySteward";

	/**
	 * This method is used by the sender to encrypt the key using the
	 * recipient's public key. A keystore is read from a location specified in
	 * the config file and contains the public key of the recipient. The alias of
	 * the recipient entry in the keystore must match the DTN security
	 * destination (server's EID). The encrypted symmetric key is obtained as a
	 * length-value pair (type is added in "generate").
	 * 
	 * @param security_dest
	 *            (): the DTN security destination, e.g. dtn://server.dtn
	 * @param data
	 *            (IN): contains the key to be encrypted.
	 * @param enc_key
	 *            (OUT): encrypted symmetric key, as a length-value pair.
	 */
	public static void encrypt(String security_dest, byte[] data,
			IByteBuffer enc_key) throws Exception {

		BPF.getInstance().getBPFLogger()
				.debug(TAG, "Encrypting symmetric key...");

		java.security.Security
				.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		KeyStore ks = KeyStore.getInstance("BKS");

		// path to the key store file
		String kspath = BPF.getInstance().getConfig().security_setting()
				.ks_path();

		// password to access the key store
		char[] password = BPF.getInstance().getConfig().security_setting()
				.ks_password().toCharArray();

		FileInputStream fis = null;
		try {
			BPF.getInstance().getBPFLogger()
					.debug(TAG, "Opening file: " + kspath);
			fis = new FileInputStream(kspath);
			BPF.getInstance().getBPFLogger()
					.debug(TAG, "Loading keystore from: " + kspath);
			ks.load(fis, password);
		} catch (Exception e) {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "Error while loading keystore!");
		} finally {
			if (fis != null) {
				fis.close();
			}
		}

		@SuppressWarnings("rawtypes")
		Enumeration en = ks.aliases();
		X509Certificate cert = null;
		while (en.hasMoreElements()) {
			String alias = (String) en.nextElement();
			BPF.getInstance()
					.getBPFLogger()
					.debug(TAG,
							"found alias: " + alias + ", isCertificate?: "
									+ ks.isCertificateEntry(alias));
			if (alias.equals(security_dest)) // make sure that you encrypt with
												// the public key of the
												// security destination
			{
				BPF.getInstance().getBPFLogger().debug(TAG, "Got certificate.");
				cert = (X509Certificate) ks.getCertificate(alias);
			}
		}

		// set up the generator
		CMSEnvelopedDataGenerator1 gen = new ext.org.bouncycastle.cms.CMSEnvelopedDataGenerator1();

		// add the recipient's public key to the cms message. This key will be
		// used to encrypt the sym key.
		// it also fills the rid field in KeyTransRecipientInfo.
		gen.addKeyTransRecipient(cert);

		byte[] key1 = new byte[16];// 128 bits
		key1 = data;
		CMSProcessable data1 = new CMSProcessableByteArray(key1);

		// create a CMSEnveloped data, by encrypting the symmetric key with the
		// public key from the certificate (cert)
		CMSEnvelopedData enveloped = gen.generate(data1,
				CMSEnvelopedDataGenerator1.AES128_CBC, "BC");

		byte[] cMS_structure = new byte[enveloped.getEncoded().length];

		cMS_structure = enveloped.getEncoded();

		BPF.getInstance()
				.getBPFLogger()
				.debug(TAG,
						"Encoding size of encrypted key. Size: "
								+ enveloped.getEncoded().length);

		SDNV.encode(enveloped.getEncoded().length, enc_key);

		// save encrypted symmetric key in enc_key
		enc_key.put(cMS_structure);

		enc_key.rewind();

	}

	/**
	 * This method is used by the recipient to decrypt the key using its own
	 * private key. A keystore is read from a location specified in the config
	 * file and contains the private key of the recipient. The alias of the
	 * recipient entry in the keystore must match the recipient local EID.
	 * 
	 * @param enc_data
	 *            (IN): buffer pointing to the encrypted symmetric key
	 * @param enc_data_len
	 *            (IN): length of the key in the buffer.
	 * @param dec_key
	 *            (OUT): decrypted symmetric key.
	 */
	public static void decrypt(IByteBuffer enc_data, long enc_data_len,
			IByteBuffer dec_key) throws Exception {
		BPF.getInstance().getBPFLogger()
				.debug(TAG, "Decrypting symmetric key...");

		int init_pos = enc_data.position();

		if (enc_data_len < 2)
			return;

		long[] len = new long[1];
		SDNV.decode(enc_data, len);
		BPF.getInstance().getBPFLogger()
				.debug(TAG, "length of CMS encoded key field: " + len[0]);

		EndpointID local_eid = BundleDaemon.getInstance().local_eid();
		String my_alias = local_eid.toString();

		java.security.Security
				.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		KeyStore ks = KeyStore.getInstance("BKS");

		// path to the key store file
		String kspath = BPF.getInstance().getConfig().security_setting()
				.ks_path();

		// password to access the key store
		char[] password = BPF.getInstance().getConfig().security_setting()
				.ks_password().toCharArray();

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(kspath);
			ks.load(fis, password);
		} finally {
			if (fis != null) {
				fis.close();
			}
		}

		PrivateKey key = null;
		key = (PrivateKey) ks.getKey(my_alias, password); // assuming that the
															// password for the
															// key
															// is the same as
															// the password for
															// the keystore
		if (key == null) {
			BPF.getInstance()
					.getBPFLogger()
					.error(TAG,
							"Private key could not be retrieved from keystore!");
			return;
		}
		BPF.getInstance().getBPFLogger().debug(TAG, "Got private key.");

		CMSEnvelopedData enveloped;

		byte[] cMS_structure = new byte[(int) len[0]];
		enc_data.get(cMS_structure);

		// re-create the CMSEnvelopedData object from its encoded form. we
		// create the binary data from the object.
		// IMPORTANT: This will normally be encoded using the BER rules rather
		// than the DER ones
		enveloped = new CMSEnvelopedData(cMS_structure);

		Certificate[] chain = ks.getCertificateChain(my_alias);
		X509Certificate cert1 = (X509Certificate) chain[0];

		// look for our recipient identifier
		RecipientId recId = new RecipientId();

		recId.setSerialNumber(cert1.getSerialNumber());
		recId.setIssuer(cert1.getIssuerX500Principal().getEncoded());

		BPF.getInstance().getBPFLogger()
				.debug(TAG, "recId: " + recId.toString());

		RecipientInformationStore recipients = enveloped.getRecipientInfos();

		// RecipientInformation recipient = recipients.get(recId);
		// Log.d(TAG, "recipients size: " + recipients.size());

		// TODO: this has been forced to get the 1st element of the Collection
		RecipientInformation recipient = (RecipientInformation) recipients
				.getRecipients().toArray()[0];
		byte[] recData = null;

		if (recipient != null) {
			// decrypt the data
			// take the private key and decrypt the encrypted symmetric key,
			// placing the symmetric key in recData.
			recData = recipient.getContent(key, "BC");
		} else {
			BPF.getInstance().getBPFLogger()
					.error(TAG, "could not find a matching recipient");
			return;
		}

		String key_str = "";
		for (int i = 0; i < recData.length; i++)
			key_str = new String(key_str
					+ String.format("%2.2h ",
							Ciphersuite_C3.unsignedByteToInt(recData[i])));

		BPF.getInstance()
				.getBPFLogger()
				.debug(TAG,
						"Decrypt(). symmetric key after decryption: 0x "
								+ key_str);

		// we copy the decrypted key
		dec_key.put(recData);

		enc_data.position(init_pos);

		return;
	}

}
