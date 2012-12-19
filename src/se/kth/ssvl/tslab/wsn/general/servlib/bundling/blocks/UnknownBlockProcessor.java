
package se.kth.ssvl.tslab.wsn.general.servlib.bundling.blocks;

import se.kth.ssvl.tslab.wsn.general.servlib.bundling.blocks.BlockInfo.list_owner_t;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.Bundle;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleProtocol.block_flag_t;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleProtocol.bundle_block_type_t;
import se.kth.ssvl.tslab.wsn.general.servlib.bundling.bundles.BundleProtocol.status_report_reason_t;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.IByteBuffer;

/**
 * Block processor implementation for any unknown bundle blocks.
 * 
 * @author Sharjeel Ahmed (sharjeel@kth.se)
 * 
 */

public class UnknownBlockProcessor extends BlockProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8927605141320447882L;
	/**
	 * Singleton instance Implementation of the BundleStore
	 */
	private static UnknownBlockProcessor instance_ = null;

	/**
	 * Singleton Implementation to get the instance of UnknownBlockProcessor
	 * 
	 * @return an singleton instance of UnknownBlockProcessor
	 */
	public static UnknownBlockProcessor getInstance() {
		if (instance_ == null) {
			instance_ = new UnknownBlockProcessor();
		}
		return instance_;
	}

	/**
	 * Constructor
	 */
	public UnknownBlockProcessor() {

		super(bundle_block_type_t.UNKNOWN_BLOCK);
	}

	/**
	 * Virtual from BlockProcessor to prepare the bundle
	 * 
	 * @param bundle
	 *            Bundle to prepare
	 * @param xmit_blocks
	 *            Empty xmit_blocks of the blundle
	 * @param source
	 *            Source endpoint id
	 * @param link
	 *            Link of the bundle
	 * @param list
	 *            Owner type
	 * @return Return success message on success
	 */
	@Override
	public int prepare(final Bundle bundle, BlockInfoVec xmit_blocks,
			final BlockInfo source, final Link link, list_owner_t list) {

		assert (source != null) : "UnknowBlockProcess: prepare() source is null";

		assert (source.owner() == this) : "UnknowBlockProcess: prepare() source.owner is not this";

		if ((source.flags() & block_flag_t.BLOCK_FLAG_DISCARD_BLOCK_ONERROR
				.getCode()) > 0) {
			return BP_FAIL;
		}

		return super.prepare(bundle, xmit_blocks, source, link, list);

	}

	/**
	 * Generate bundle block.
	 * 
	 * @param bundle
	 *            Bundle to generate
	 * @param xmit_blocks
	 *            xmit_blocks to get the dictionary for generating
	 * @param block
	 *            Block to write the data
	 * @param link
	 *            Link type
	 * @param last
	 *            If its a last block
	 * @return If successfully generated then return Success message.
	 */
	@Override
	public int generate(final Bundle bundle, BlockInfoVec xmit_blocks,
			BlockInfo block, final Link link, boolean last) {

		final BlockInfo source = block.source();
		assert (source != null) : "UnKnownBlockProcess: generate, source is null";
		assert (source.owner() == this) : "UnKnownBlockProcess: generate, this==owner";

		assert ((source.flags() & block_flag_t.BLOCK_FLAG_DISCARD_BUNDLE_ONERROR
				.getCode()) == 0) : "UnKnownBlockProcess: Flag set BLOCK_FLAG_DISCARD_BUNDLE_ONERROR";
		assert ((source.flags() & block_flag_t.BLOCK_FLAG_DISCARD_BLOCK_ONERROR
				.getCode()) == 0) : "UnKnownBlockProcess: Flag set BLOCK_FLAG_DISCARD_BLOCK_ONERROR";

		assert (source.contents().capacity() != 0) : "UnKnownBlockProcess: no data";
		assert (source.data_offset() != 0) : "UnKnownBlockProcess: Data offset 0";

		int flags = source.flags();

		if (last) {
			flags |= block_flag_t.BLOCK_FLAG_LAST_BLOCK.getCode();
		} else {
			flags &= block_flag_t.BLOCK_FLAG_LAST_BLOCK.getCode();
		}
		flags |= block_flag_t.BLOCK_FLAG_FORWARDED_UNPROCESSED.getCode();

		block.set_eid_list(source.eid_list());

		generate_preamble(xmit_blocks, block, source.type(), flags,
				source.data_length());

		assert (block.data_length() == source.data_length()) : "UnKnownBlockProcess: block and source length not equal";

		IByteBuffer contents = block.writable_contents();

		contents.position(block.data_offset());
		source.contents().position(source.data_offset());

		return BP_SUCCESS;

	}

	/**
	 * Check the validity of the the bundle
	 * 
	 * @param bundle
	 *            Bundle to check its generic validity
	 * @param block_list
	 *            List of Blocks
	 * @param block
	 *            Block to check if it's valid or not
	 * @param reception_reason
	 *            If block is not valid then reception reason
	 * @param deletion_reason
	 *            If block is not balid then deletion reason
	 * @return True if the block is valid else false
	 */
	@Override
	public boolean validate(final Bundle bundle, BlockInfoVec block_list,
			BlockInfo block, status_report_reason_t[] reception_reason,
			status_report_reason_t[] deletion_reason) {

		// check for generic block errors
		if (!super.validate(bundle, block_list, block, reception_reason,
				deletion_reason)) {
			return false;
		}

		// extension blocks of unknown type are considered to be "invalid"
		if ((block.flags() & block_flag_t.BLOCK_FLAG_REPORT_ONERROR.getCode()) > 0) {
			reception_reason[0] = status_report_reason_t.REASON_BLOCK_UNINTELLIGIBLE;
		}

		if ((block.flags() & block_flag_t.BLOCK_FLAG_DISCARD_BUNDLE_ONERROR
				.getCode()) > 0) {
			deletion_reason[0] = status_report_reason_t.REASON_BLOCK_UNINTELLIGIBLE;
			return false;
		}

		return true;

	}
}
