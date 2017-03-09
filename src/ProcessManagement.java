import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ProcessManagement {

    //set the working directory
    private static File currentDirectory = new File(System.getProperty("user.dir"));
    //set the instructions file
    private static File instructionSet = new File("graph-file1.txt");
    public static Object lock=new Object();

    public static void main(String[] args) throws InterruptedException {

        //parse the instruction file and construct a data structure, stored inside ProcessGraph class
        ParseFile.generateGraph(new File(currentDirectory + File.separator+"src"+File.separator+instructionSet));

        // Print the graph information
        ProcessGraph.printGraph();
	// WRITE YOUR CODE
        while(ProcessGraph.nodes.size()>0){
        	Iterator<ProcessGraphNode> iter = ProcessGraph.nodes.iterator();
        	while(iter.hasNext()){
        		ProcessGraphNode indvNode = iter.next();
        		if(!indvNode.isExecuted()){			// If Node has not been executed
            		if(indvNode.isRunnable()){		// and is runnable
            			execute(indvNode.getCommand(),indvNode.getInputFile(),indvNode.getOutputFile());
            			iter.remove();
            		}
            	}else{
            		// Removes completed processes from the Process Graph
            		// This helps to reduce the number of objects iterated through
            		// in above for loop
            		iter.remove();
            	}
        	}
        	
//        	for(ProcessGraphNode indvNode : ProcessGraph.nodes){
//            	if(!indvNode.isExecuted()){
//            		if(indvNode.isRunnable()){
//            			execute(indvNode.getCommand(),indvNode.getInputFile(),indvNode.getOutputFile());
//            			ProcessGraph.nodes.remove(indvNode);
//            		}
//            	}else{
//            		// Removes completed processes from the Process Graph
//            		// This helps to reduce the number of objects iterated through
//            		// in above for loop
//            		ProcessGraph.nodes.remove(indvNode);
//            	}
//            	
//            }
        	
        }
        
        // Using index of ProcessGraph, loop through each ProcessGraphNode, to check whether it is ready to run
                // check if all the nodes are executed
                // WRITE YOUR CODE

                //mark all the runnable nodes
	        // WRITE YOUR CODE

                //run the node if it is runnable
	        // WRITE YOUR CODE

        System.out.println("All process finished successfully");
    }

	private static void execute(String command, File inputFile, File outputFile) {
		// TODO Auto-generated method stub
		String[] commandList = command.split(" ");
		ProcessBuilder pBuilder = new ProcessBuilder(commandList);
		pBuilder.directory(currentDirectory);
		if(!inputFile.getName().equals("stdin")){
			pBuilder.redirectInput(inputFile);
		}
		if(!outputFile.getName().equals("stdout")){
			pBuilder.redirectOutput(outputFile);
		}
		
		try {
			Process p = pBuilder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}