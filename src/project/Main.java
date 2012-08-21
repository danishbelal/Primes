package project;

import project.model.Prime;
import project.view.GUI;


/**
 * @author Danish Belal
 * This Class is Testing the functionality of other Classes (,yet)
 */

public class Main
{

    static Prime x=new Prime();

    public static void main(String[] args)
    {

        int[] pr=x.calcPrimes(10);
        for (int i=0; i<pr.length; i++)
        {
            if (pr[i]!=0)
                System.out.println(pr[i]);
        }

        GUI gui = new GUI();
        gui.initGUI();
        
        
    }
}
