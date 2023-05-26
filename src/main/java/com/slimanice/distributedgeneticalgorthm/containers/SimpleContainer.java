package com.slimanice.distributedgeneticalgorthm.containers;

import com.slimanice.distributedgeneticalgorthm.agents.IslandAgent;
import com.slimanice.distributedgeneticalgorthm.agents.MasterAgent;
import com.slimanice.distributedgeneticalgorthm.utils.GAUtils;
import jade.core.AID;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.tools.sniffer.Agent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.util.ArrayList;
import java.util.List;

public class SimpleContainer {
    public static void main(String[] args) {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        AgentContainer agentContainer = runtime.createMainContainer(profile);

        for (int i = 0; i< GAUtils.NUMBER_OF_ISLANDS; i++){
            try {
                AgentController islandAgent = agentContainer.createNewAgent("island " + (i + 1), IslandAgent.class.getName(), new Object[]{});
                islandAgent.start();
            }catch (StaleProxyException e) {
                System.out.println("Error while starting IslandAgent " + i);
                throw new RuntimeException(e);
            }
        }
        try {
            AgentController masterAgent = agentContainer.createNewAgent("MasterAgent", MasterAgent.class.getName(), new Object[]{});
            masterAgent.start();
        } catch (StaleProxyException e) {
            System.out.println("Error while starting MasterAgent");
            throw new RuntimeException(e);
        }
    }
}
