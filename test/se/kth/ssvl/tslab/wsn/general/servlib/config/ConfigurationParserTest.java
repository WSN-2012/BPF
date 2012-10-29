/**
 * 
 */
package se.kth.ssvl.tslab.wsn.general.servlib.config;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;

import se.kth.ssvl.tslab.wsn.general.bpf.BPF;
import se.kth.ssvl.tslab.wsn.general.servlib.config.exceptions.InvalidDTNConfigurationException;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.DiscoveriesSetting;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.DiscoveriesSetting.AnnounceEntry;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.DiscoveriesSetting.DiscoveryEntry;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.DiscoveriesSetting.address_family_t;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.InterfacesSetting;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.LinksSetting;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.InterfacesSetting.InterfaceEntry;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.LinksSetting.LinkEntry;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.RoutesSetting;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.RoutesSetting.RouteEntry;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.SecuritySetting;
import se.kth.ssvl.tslab.wsn.general.servlib.config.settings.StorageSetting;
import se.kth.ssvl.tslab.wsn.general.servlib.config.types.conv_layer_type_t;
import se.kth.ssvl.tslab.wsn.general.servlib.contacts.links.Link.link_type_t;
import se.kth.ssvl.tslab.wsn.general.servlib.conv_layers.ConvergenceLayer;
import se.kth.ssvl.tslab.wsn.general.servlib.routing.prophet.queuing.ProphetQueuing.QueueingType;
import se.kth.ssvl.tslab.wsn.general.servlib.routing.routers.BundleRouter.router_type_t;
import se.kth.ssvl.tslab.wsn.general.systemlib.util.*;


/**
 * @author natalia
 * 
 */
public class ConfigurationParserTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link se.kth.ssvl.tslab.wsn.general.servlib.config.ConfigurationParser#parse_config_file(java.io.InputStream)}
	 * .
	 * 
	 * @throws FileNotFoundException
	 * @throws InvalidDTNConfigurationException
	 */
	@Test
	public void testParse_config_file()
			throws InvalidDTNConfigurationException, FileNotFoundException {

		Configuration conf = ConfigurationParser
				.parse_config_file(new java.io.FileInputStream(
						new File(
								"test/se/kth/ssvl/tslab/wsn/general/servlib/config/dtn.config.xml")));

		// Create an Interfaces Setting using the variables written in the
		// configuration file
		InterfacesSetting configurationInterfacesSetting = new InterfacesSetting();
		/*
		 * Create a List of Interface Entries present in the configuration file
		 * to pass it to the InterfacesSetting object
		 */
		List<InterfaceEntry> configurationInterfaceEntries = new List();
		InterfaceEntry interfaceEntry1 = new InterfaceEntry();
		interfaceEntry1.set_id("tcp0");
		interfaceEntry1.set_conv_layer_type(conv_layer_type_t.TCP);
		interfaceEntry1.set_local_port((short) 4556);
		configurationInterfaceEntries.add(interfaceEntry1);
		configurationInterfacesSetting
				.set_interface_entries(configurationInterfaceEntries);

		// List of the interface Entries parsed from the configuration
		List<InterfaceEntry> interfaceEntries = conf.interfaces_setting()
				.interface_entries();
		// check if interface entry attributes are parsed correctly from the
		// configuration file
		for (int i = 0; i < interfaceEntries.size(); i++) {
			assertEquals(configurationInterfacesSetting.interface_entries()
					.get(i).id(), interfaceEntries.get(i).id());
			assertEquals(configurationInterfacesSetting.interface_entries()
					.get(i).conv_layer_type(), interfaceEntries.get(i)
					.conv_layer_type());
			assertEquals(configurationInterfacesSetting.interface_entries()
					.get(i).local_port(), interfaceEntries.get(i).local_port());
		}

		// Create an Links Setting using the variables written in the
		// configuration file
		LinksSetting configurationLinksSetting = new LinksSetting();
		// set parameters of Links Setting
		configurationLinksSetting.set_proactive_fragmentation(true);
		configurationLinksSetting.set_fragmentation_mtu(5000);

		/*
		 * Create a List of Interface Entries present in the configuration file
		 * to pass it to the InterfacesSetting object
		 */
		List<LinkEntry> configurationLinkEntries = new List();
		LinkEntry linkEntry1 = new LinkEntry();
		linkEntry1.set_id("bifrost_tcp_link");
		linkEntry1.set_conv_layer_type(conv_layer_type_t.TCP);
		linkEntry1.set_des("192.168.0.1:4556");
		linkEntry1.set_type(link_type_t.ONDEMAND);
		configurationLinkEntries.add(linkEntry1);
		configurationLinksSetting.set_link_entries(configurationLinkEntries);

		// check LinksSetting attributes
		assertEquals(configurationLinksSetting.proactive_fragmentation(), conf
				.links_setting().proactive_fragmentation());
		assertEquals(configurationLinksSetting.fragmentation_mtu(), conf
				.links_setting().fragmentation_mtu());

		// List of the link Entries parsed from the configuration
		List<LinkEntry> linkEntries = conf.links_setting().link_entries();
		// check if link entry attributes are parsed correctly from the
		// configuration file
		for (int i = 0; i < linkEntries.size(); i++) {
			assertEquals(configurationLinksSetting.link_entries().get(i).id(),
					linkEntries.get(i).id());
			assertEquals(configurationLinksSetting.link_entries().get(i)
					.conv_layer_type(), linkEntries.get(i).conv_layer_type());
			assertEquals(configurationLinksSetting.link_entries().get(i).des(),
					linkEntries.get(i).des());
			assertEquals(
					configurationLinksSetting.link_entries().get(i).type(),
					linkEntries.get(i).type());
		}

		// Create a Routes Setting using the variables written in the
		// configuration file
		RoutesSetting configurationRoutesSetting = new RoutesSetting();
		// set parameters of Routes Setting
		configurationRoutesSetting
				.set_router_type(router_type_t.PROPHET_BUNDLE_ROUTER);
		configurationRoutesSetting.set_local_eid("dtn://linux.dtn");
		if(BPF.getInstance()!=null)
			configurationRoutesSetting.setQueuing_policy(QueueingType.FIFO);
		else{
			
		}

		/*
		 * Create a List of Route Entries present in the configuration file to
		 * pass it to the RoutesSetting object
		 */
		List<RouteEntry> configurationRouteEntries = new List();
		RouteEntry routeEntry1 = new RouteEntry();
		RouteEntry routeEntry2 = new RouteEntry();
		RouteEntry routeEntry3 = new RouteEntry();
		routeEntry1.set_dest("dtn://city.bytewalla.com/*");
		routeEntry1.set_link_id("bifrost_tcp_link");
		routeEntry2.set_dest("dtn://village.bytewalla.com/*");
		routeEntry2.set_link_id("bifrost_tcp_link");
		routeEntry3.set_dest("dtn://bifrost.bytewalla.com/*");
		routeEntry3.set_link_id("bifrost_tcp_link");
		configurationRouteEntries.add(routeEntry1);
		configurationRouteEntries.add(routeEntry2);
		configurationRouteEntries.add(routeEntry3);
		configurationRoutesSetting.set_route_entries(configurationRouteEntries);

		// check RoutesSetting attributes
		assertEquals(configurationRoutesSetting.router_type(), conf
				.routes_setting().router_type());
		assertEquals(configurationRoutesSetting.local_eid(), conf
				.routes_setting().local_eid());
		assertEquals(configurationRoutesSetting.getQueuing_policy(), conf
				.routes_setting().getQueuing_policy());

		// List of the route Entries parsed from the configuration
		List<RouteEntry> routeEntries = conf.routes_setting().route_entries();
		// check if route entry attributes are parsed correctly from the
		// configuration file
		for (int i = 0; i < routeEntries.size(); i++) {
			assertEquals(configurationRoutesSetting.route_entries().get(i)
					.dest(), routeEntries.get(i).dest());
			assertEquals(configurationRoutesSetting.route_entries().get(i)
					.link_id(), routeEntries.get(i).link_id());
		}

		// Create a Discoveries Setting using the variables written in the
		// configuration file
		DiscoveriesSetting configurationDiscoveriesSetting = new DiscoveriesSetting();

		/*
		 * Create a List of Discovery Entries present in the configuration file
		 * to pass it to the DiscoveriesSetting object
		 */
		List<DiscoveryEntry> configurationDiscoveryEntries = new List();
		DiscoveryEntry discoveryEntry1 = new DiscoveryEntry();
		discoveryEntry1.set_id("ipdisc0");
		discoveryEntry1.set_address_family(address_family_t.IP);
		discoveryEntry1.set_port((short) 9556);
		configurationDiscoveryEntries.add(discoveryEntry1);
		configurationDiscoveriesSetting
				.set_discovery_entries(configurationDiscoveryEntries);
		/*
		 * Create a List of Announce Entries present in the configuration file
		 * to pass it to the DiscoveriesSetting object
		 */
		List<AnnounceEntry> configurationAnnounceEntries = new List();
		AnnounceEntry announceEntry1 = new AnnounceEntry();
		announceEntry1.set_interface_id("tcp0");
		announceEntry1.set_discovery_id("ipdisc0");
		announceEntry1.set_conv_layer_type(conv_layer_type_t.TCP);
		announceEntry1.set_interval(5);// if interval is not specified 5 is the
										// default value
		configurationAnnounceEntries.add(announceEntry1);
		configurationDiscoveriesSetting
				.set_announce_entries(configurationAnnounceEntries);

		// check RoutesSetting attributes
		assertEquals(configurationRoutesSetting.router_type(), conf
				.routes_setting().router_type());
		assertEquals(configurationRoutesSetting.local_eid(), conf
				.routes_setting().local_eid());
		assertEquals(configurationRoutesSetting.getQueuing_policy(), conf
				.routes_setting().getQueuing_policy());

		// List of the Discovery Entries parsed from the configuration
		List<DiscoveryEntry> discoveryEntries = conf.discoveries_setting()
				.discovery_entries();
		// check if route entry attributes are parsed correctly from the
		// configuration file
		for (int i = 0; i < discoveryEntries.size(); i++) {
			assertEquals(configurationDiscoveriesSetting.discovery_entries()
					.get(i).id(), discoveryEntries.get(i).id());
			assertEquals(configurationDiscoveriesSetting.discovery_entries()
					.get(i).address_family(), discoveryEntries.get(i)
					.address_family());
			assertEquals(configurationDiscoveriesSetting.discovery_entries()
					.get(i).port(), discoveryEntries.get(i).port());
		}
		// List of the Discovery Entries parsed from the configuration
		List<AnnounceEntry> announceEntries = conf.discoveries_setting()
				.announce_entries();
		// check if route entry attributes are parsed correctly from the
		// configuration file
		for (int i = 0; i < announceEntries.size(); i++) {
			assertEquals(configurationDiscoveriesSetting.announce_entries()
					.get(i).interface_id(), announceEntries.get(i)
					.interface_id());
			assertEquals(configurationDiscoveriesSetting.announce_entries()
					.get(i).discovery_id(), announceEntries.get(i)
					.discovery_id());
			assertEquals(configurationDiscoveriesSetting.announce_entries()
					.get(i).conv_layer_type(), announceEntries.get(i)
					.conv_layer_type());
			assertEquals(configurationDiscoveriesSetting.announce_entries()
					.get(i).interval(), announceEntries.get(i).interval());
		}

		// Create a Storage Setting using the variables written in the
		// configuration file
		StorageSetting configurationStorageSetting = new StorageSetting();
		// set parameters of Storage Setting
		configurationStorageSetting
				.set_storage_path("test/se/kth/ssvl/tslab/wsn/general/servlib/config/build/dtn");
		configurationStorageSetting.set_quota(1);
		configurationStorageSetting.set_test_data_log(true);

		// check LinksSetting attributes
		assertEquals(configurationStorageSetting.storage_path(), conf
				.storage_setting().storage_path());
		assertEquals(configurationStorageSetting.quota(), conf
				.storage_setting().quota());
		assertEquals(configurationStorageSetting.test_data_log(), conf
				.storage_setting().test_data_log());

		// Create a Security Setting using the variables written in the
		// configuration file
		/*SecuritySetting configurationSecuritySetting = new SecuritySetting();
		// set parameters of Storage Setting
		configurationSecuritySetting.set_ks_path(ksPath);
		configurationSecuritySetting.set_ks_password(ksPassword);
		configurationSecuritySetting.set_use_pcb(usePCB);
		configurationSecuritySetting.set_use_pib(usePIB)
		
		// check LinksSetting attributes
		assertEquals(configurationSecuritySetting.ks_path(), conf
				.security_setting().ks_path());
		assertEquals(configurationSecuritySetting.ks_password(), conf
				.security_setting().ks_password());
		assertEquals(configurationSecuritySetting.use_pcb(), conf
				.security_setting().use_pcb());
		assertEquals(configurationSecuritySetting.use_pib(), conf
				.security_setting().use_pib());*/
	}

}
