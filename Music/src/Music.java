import jm.JMC;
import jm.util.*;
import jm.music.data.*;


import java.util.Scanner;

public class Music {

    private static Score _score = new Score("Pi Song");
    private static Part _bass = new Part("left hand", 0, 1);

    private static int _key;
    private static int[] _scale;

    public static void main(String[] args) {
        _key = JMC.C3;
        _scale = MAJOR;
        playString(PI);
        //playString("0123456789");
    }

    private static void playString(String str) {
        Part mainPiano = new Part("Electric_Piano", 0, 0);
        addString(str, mainPiano);

        _score.addPart(mainPiano);

        View.show(_score);
        Play.midi(_score);
    }

    private static void addString(String str, Part part) {
        Scanner knight = new Scanner(str);
        knight.useDelimiter("");

        while (knight.hasNext()) {
            String val = knight.next();
            int digit = Integer.parseInt(val);

            Note note = new Note(_scale[digit] + _key, rhythm());
            part.addNote(note, currentTime());
        }

    }

    private static double _currentTime = 0;
    private static double _realTime = 0;

    private static double currentTime() {
        double out = _realTime;
        _realTime = _currentTime;
        return out;
    }

    private static int _numNotes = 0;

    private static double rhythm() {
        if (_numNotes % 3 == 0) {
            _numNotes++;
            return quarter();
        }
        _numNotes++;
        return eight();
    }

    private static double sixteenth() {
        _currentTime += _rhythms[0];
        return _rhythms[0];
    }


    private static double eight() {
        _currentTime += _rhythms[1];
        return _rhythms[1];
    }

    private static double quarter() {
        _currentTime += _rhythms[2];
        return _rhythms[2];
    }

    private static double half() {
        _currentTime += _rhythms[3];
        return _rhythms[3];
    }

    private static double whole() {
        _currentTime += _rhythms[4];
        return _rhythms[4];
    }

    private static double[] _rhythms = new double[] {0.25, 0.5, 1.0, 2.0, 4.0};

    private static final int[] AEOLIAN = {-2, 0, 2, 3, 5, 7, 8, 10, 12, 14};
    private static final int[] MINOR = AEOLIAN;
    private static final int[] LOCRIAN = {-2, 0, 1, 3, 5, 6, 8, 10, 12, 13};
    private static final int[] IONIAN = {-1, 0, 2, 4, 5, 7, 9, 11, 12, 14};
    private static final int[] MAJOR = IONIAN;
    private static final int[] DORIAN = null; /** Todo */
    private static final int[] PHRYGIAN = null; /** Todo */
    private static final int[] LYDIAN = null; /** Todo */
    private static final int[] MIXOLYDIAN = null; /** Todo */

    private static final int[] MINOR_PENT = {-2, 0, 3, 5, 7, 10, 12, 15, 17, 19};
    private static final int[] BLUES = MINOR_PENT;
    private static final int[] MAJOR_PENT = {-3, 0, 2, 4, 7, 9, 12, 14, 17, 19};
    /** Todo: The three remaining pentatonic scales. */




    private static final String PI = "3141592" +
            "65358979323846264338327950288419716" +
            "939937510582097494459230781650628620" +
            "899862803482534211706798214808651328";
}
