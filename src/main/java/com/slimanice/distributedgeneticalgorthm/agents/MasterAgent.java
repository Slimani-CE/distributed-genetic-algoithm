package com.slimanice.distributedgeneticalgorthm.agents;

import com.slimanice.distributedgeneticalgorthm.controllers.MasterGuiController;
import com.slimanice.distributedgeneticalgorthm.utils.GAUtils;
import com.slimanice.distributedgeneticalgorthm.utils.Solution;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MasterAgent extends GuiAgent{
    int solutionsFound = 0;
    List<Solution> solutionList = new ArrayList<>();
    MasterGuiController masterGuiController;

    @Override
    protected void setup() {
        // Get the number of islands
        int numberOfIslands = GAUtils.NUMBER_OF_ISLANDS;

        // Register the master agent in the DF
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("master");
        sd.setName(getLocalName());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }
        // Cyclic behaviour to handle messages
        addBehaviour(new Behaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    String content = msg.getContent();
                    if (msg.getConversationId().equals("request-parameters")) {
                        // Send parameters to the island agent
                        String type = "parameters";
                        String parameters = "target=" + GAUtils.TARGET + ";maxIterations=" + GAUtils.MAX_ITERATIONS + ";populationSize=" + GAUtils.POPULATION_SIZE;
                        sendMsg(msg.getSender(), type, parameters);
                    }
                    else if (msg.getConversationId().equals("first-fittest")) {
                        String solution = content.split("\\|")[0];
                        String fitness = content.split("\\|")[1];
                        solutionsFound++;
                        solutionList.add(new Solution(msg.getSender(), solution, Integer.parseInt(fitness)));
                    }
                }
                else {
                    block();
                }
            }

            @Override
            public boolean done() {
                if(solutionsFound == numberOfIslands)
                    displayResults();
                return solutionsFound == numberOfIslands;
            }

            private void displayResults() {
                // Sort the solutions by fitness
                solutionList.sort(Comparator.comparingInt(Solution::getFitness));
                System.out.println("All solutions found:");
                for(Solution solution : solutionList){
                    System.out.println("Island: " + solution.getAid().getLocalName() + "; solution: " + solution.getSolution() + "; fitness: " + solution.getFitness());
                }
            }

            private void sendMsg(AID sender, String type, String parameters) {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(sender);
                msg.setConversationId(type);
                msg.setContent(parameters);
                send(msg);
            }
        });
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }
}
