import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseFile {
    //this method generates a ProcessGraph and store in ProcessGraph Class
    public static void generateGraph(File inputFile) {
        try{
            Scanner fileIn=new Scanner(inputFile);
            ArrayList<String> listOfChildrenId = new ArrayList<String>();
            int index=0;
            while(fileIn.hasNext()){
                String line=fileIn.nextLine();
                String[] quatiles= line.split(":"); // name arg: children : input : output
                if (quatiles.length!=4) {
                    System.out.println("Wrong input format!");
                    throw new Exception();
                }

                //add this node
                ProcessGraph.addNode(index);
                //handle Children
                listOfChildrenId.add(quatiles[1]);
                
                //setup command
                ProcessGraph.nodes.get(index).setCommand(quatiles[0]);
                //setup input
                ProcessGraph.nodes.get(index).setInputFile(new File(quatiles[2]));
                //setup output
                ProcessGraph.nodes.get(index).setOutputFile(new File(quatiles[3]));
                
                //mark initial runnable
                for (ProcessGraphNode node:ProcessGraph.nodes) {
                    if (node.getParents().isEmpty()){
                        node.setRunnable();
                    }
                }


                index++;
            }
            
            //setup parent
            for (ProcessGraphNode node : ProcessGraph.nodes) {
                for (ProcessGraphNode childNode : node.getChildren()) {
                    ProcessGraph.nodes.get(childNode.getNodeId()).addParent(ProcessGraph.nodes.get(node.getNodeId()));
                }
            }
            
            for(int j = 0;j<index;j++){
            	if (!listOfChildrenId.get(j).equals("none")){ // If there are children nodes
                    String[] childrenStringArray=listOfChildrenId.get(j).split(" ");
                    int[] childrenId=new int[childrenStringArray.length];
                    for (int i = 0; i < childrenId.length; i++) { // Iterates through the children IDs
                        childrenId[i]= Integer.parseInt(childrenStringArray[i]);
                        ProcessGraph.addNode(childrenId[i]);
                        ProcessGraph.nodes.get(j).addChild(ProcessGraph.nodes.get(childrenId[i]));
                    }
                }
            }
            fileIn.close();
        } catch (Exception e){
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }


}