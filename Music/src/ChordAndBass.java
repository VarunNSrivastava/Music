import jm.JMC;
import jm.util.*;
import jm.music.data.*;
import jm.util.*;

/**
 * This class uses the jMusic CPhrase (Chord Phrase)
 * @author Tara Simmonds and Andrew Brown
 */
class ChordsAndBass {

    private  Score s = new Score("CPhrase class example");
    private  Part p = new Part("Piano", 0, 0);
    private Part bassPart = new Part("left hand", 0, 1);
    private double[] rhythms = new double[] {0.25, 0.5, 1.0, 2.0, 4.0};

    public static void main(String[] args){
        new ChordsAndBass();
    }

    public ChordsAndBass() {
        for (int i = 0; i < 8; i++) {
            triad((int)(Math.random() * 36 + 48));
            bassNote((int)(Math.random() * 24 + 24));
        }

        //pack the part into a score
        s.addPart(p);
        s.addPart(bassPart);

        //display the music
        View.show(s);

        // write the score to a MIDIfile
        //Write.midi(s, "ChordsAndBass.mid");
        Play.midi(s);

    }

    private  void triad(int rootPitch) {
        // build the chord from the rootPitch
        int[] pitchArray = new int[3];
        pitchArray[0] = rootPitch;
        if(Math.random() > 0.5) {
            pitchArray[1] = rootPitch + 4; // major
        } else pitchArray[1] = rootPitch + 3; // minor
        pitchArray[2] = rootPitch + 7;
        //add chord to the part
        CPhrase chord = new CPhrase();
        chord.addChord(pitchArray, rhythms[(int)(Math.random() * rhythms.length)]);
        p.addCPhrase(chord);
    }

    private void bassNote(int pitch) {
        bassPart.addPhrase(new Phrase(new Note(pitch, rhythms[(int)(Math.random() * rhythms.length)])));
    }


}