package hu.blummers.bitcoin.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.blummers.bitcoin.messages.GetBlocksMessage;
import hu.blummers.bitcoin.messages.MessageFactory;
import hu.blummers.p2p.P2P;
import hu.blummers.p2p.P2P.Peer;


public class ChainLoader {
	private static final Logger log = LoggerFactory.getLogger(ChainLoader.class);

	ChainStore store;
	BitcoinNetwork network;

	public ChainLoader (BitcoinNetwork network, ChainStore store)
	{
		this.network = network;
		this.store = store;
	}
	
	public void start ()
	{
		try {
			String head = store.getHead();

			// ask peers for list of blocks after our head
			
			
		} catch (Exception e) {
			log.error("Could not start chain loader", e);
		}
	}
	
	public void startDownloading (final BitcoinNetwork network, final ChainStore chainstore)
	{
		network.forAllConnected(new P2P.PeerTask () {
			@Override
			public void run(Peer peer) {
				BitcoinPeer bp = (BitcoinPeer)peer;
				GetBlocksMessage gbm = (GetBlocksMessage)MessageFactory.createMessage(network.getChain(), "getblocks");
				peer.send(gbm);
			}});
	}
}