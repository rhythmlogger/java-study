import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class Main {
	public static final int NOTE_ON = 0x90;
	public static final int NOTE_OFF = 0x80;
	public static final String[] NOTE_NAMES = { "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B" };
	static String[] strs1 = { "1", "!", "2", "@", "3", "4", "$", "5", "%", "6", "^", "7", "8", "*", "9", "(", "0", "q",
			"Q", "w", "W", "e", "E", "r", "t", "T", "y", "Y", "u", "i", "I", "o", "O", "p", "P", "a", "s", "S", "d",
			"D", "f", "g", "G", "h", "H", "j", "J", "k", "l", "L", "z", "Z", "x", "c", "C", "v", "V", "b", "B", "n",
			"m", "M" };
	static int idx = 0;
	static Map<String, String> map = new HashMap<String, String>();

	public static void main(String[] args) throws Exception {
		Sequence sequence = MidiSystem.getSequence(new File("test.mid"));

		for (int i = 2; i < 8; i++) {
			for (int j = 0; j < NOTE_NAMES.length; j++) {
				map.put(NOTE_NAMES[j] + i, strs1[idx]);
//				System.out.println(sequence[j]+i);
				if (idx == strs1.length - 1) {
					break;
				}
				idx++;
			}
		}
		System.out.println(map);
		int trackNumber = 0;
		for (Track track : sequence.getTracks()) {
			trackNumber++;
			System.out.println("Track " + trackNumber + ": size = " + track.size());
			System.out.println();
			for (int i = 0; i < track.size(); i++) {
				MidiEvent event = track.get(i);
				System.out.print("@" + event.getTick() + " ");
				MidiMessage message = event.getMessage();
				if (message instanceof ShortMessage) {
					ShortMessage sm = (ShortMessage) message;
					System.out.print("Channel: " + sm.getChannel() + " ");
					if (sm.getCommand() == NOTE_ON) {
						int key = sm.getData1();
						int octave = (key / 12) - 1;
						int note = key % 12;
						String noteName = NOTE_NAMES[note];
						int velocity = sm.getData2();
						System.out.println("Note on, " + noteName + octave + " key=" + key + " velocity: " + velocity);
						System.out.println(map.get(noteName + octave) + " On");
					} else if (sm.getCommand() == NOTE_OFF) {
						int key = sm.getData1();
						int octave = (key / 12) - 1;
						int note = key % 12;
						String noteName = NOTE_NAMES[note];
						int velocity = sm.getData2();
						System.out.println("Note off, " + noteName + octave + " key=" + key + " velocity: " + velocity);
						System.out.println(map.get(noteName + octave) + " Off");
					} else {
						System.out.println("Command:" + sm.getCommand());
					}
				} else {
					System.out.println("Other message: " + message.getClass());
				}
			}

			System.out.println();
		}

	}
}
