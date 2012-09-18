import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Factory {
    public void loadModel (Simulation sim, File modelFile) {
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (type.equals("mass")) {
                        sim.add(massCommand(line));
                    }
                    else if (type.equals("spring")) {
                        sim.add(springCommand(line, sim));
                    }
                }
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }

    private Mass massCommand (Scanner line) {
        int id = line.nextInt();
        double x = line.nextDouble();
        double y = line.nextDouble();
        double mass = line.nextDouble();
        
        if(mass < 0){
            return new FixedMass(id, x, y, mass);
        }
        
        return new Mass(id, x, y, mass);
    }

    private Spring springCommand (Scanner line, Simulation sim) {
        int m1 = line.nextInt();
        int m2 = line.nextInt();
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        double amp = -1;

        if (line.hasNext()){
            amp = line.nextDouble();
            return new Muscle(sim.getMass(m1), sim.getMass(m2), restLength, ks, amp);
        }

        if (ks < 0){
            return new Bar(sim.getMass(m1), sim.getMass(m2), restLength, ks);
        }

        return new Spring(sim.getMass(m1), sim.getMass(m2), restLength, ks);
    }
}
