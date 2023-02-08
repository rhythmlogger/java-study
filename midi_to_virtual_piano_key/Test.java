import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		String[] strs1 = { "1", "!", "2", "@", "3", "4", "$", "5", "%", "6", "^", "7", "8", "*", "9", "(", "0", "q",
				"Q", "w", "W", "e", "E", "r", "t", "T", "y", "Y", "u", "i", "I", "o", "O", "p", "P", "a", "s", "S", "d",
				"D", "f", "g", "G", "h", "H", "j", "J", "k", "l", "L", "z", "Z", "x", "c", "C", "v", "V", "b", "B", "n",
				"m", "M" };
		String [] sequence = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
		int idx =0 ;
		Map<String, String> map = new HashMap<String, String>();
		for (int i=2;i<8;i++) {
			for (int j=0; j<sequence.length;j++) {
				map.put(sequence[j]+i, strs1[idx]);
//				System.out.println(sequence[j]+i);
				if (idx == strs1.length-1) {
					break;
				}
				idx++;
			}
		}
		System.out.println(map);
		
	}
}
