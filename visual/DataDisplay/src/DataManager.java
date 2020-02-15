import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class DataManager {

	private String fileName;
	private String delimiter;
	private ArrayList<String> names;
	private ArrayList<double[]> data;
	
	public DataManager(String fileName) {
		this.fileName = fileName;
		delimiter = "[\\n, ]";
		names = new ArrayList<>();
		data = new ArrayList<>();
		initalizeData();
	}
	
	private void initalizeData() {
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName)).useDelimiter(Pattern.compile(delimiter));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		boolean inHeader = true;
		String initial;
		while(inHeader && s.hasNext()) {
			initial = s.next();
			inHeader = initial.charAt(0) != '0';
			if (inHeader) {
				names.add(initial);
			}
		}
		int elems = names.size();
		data.add(new double[elems]);
		data.get(0)[0] = 0;
		Scanner tmp = new Scanner(s.nextLine()).useDelimiter(Pattern.compile(delimiter));
		int index = 1;
		while (index < elems) {
			data.get(0)[index] = tmp.nextDouble();
			index++;
		}
		while(s.hasNextLine()) {
			tmp = new Scanner(s.nextLine()).useDelimiter(Pattern.compile(delimiter));
			data.add(new double[elems]);
			index = 0;
			while (index < elems) {
				data.get(data.size() - 1)[index] = tmp.nextDouble();
				index++;
			}
		}
		System.out.println(names);
	}
	
	public ArrayList<ArrayList<DataPoint>> getData(String n1, ArrayList<String> n2) {
		int index1 = names.indexOf(n1);
		ArrayList<Integer> depIndexes = getDependentIndexes(n2);
		if (index1 == -1) {
			System.out.println("Independent variable did not have any data. Returning null");
			return null;
		}
		else if (depIndexes.size() == 0) {
			System.out.println("No data detected for dependent variables. Returning null");
			return null;
		}
		ArrayList<ArrayList<DataPoint>> res = new ArrayList<ArrayList<DataPoint>>();
		for (int i = 0; i < n2.size(); i++) {
			ArrayList<DataPoint> nextData = new ArrayList<>();
			for (int j = 0; j < data.size(); j++) {
				double[] tmp = data.get(j);
				nextData.add(new DataPoint(tmp[index1], tmp[depIndexes.get(i)]));
			}
			res.add(nextData);
		}
		return res;
	}
	
	private ArrayList<Integer> getDependentIndexes(ArrayList<String> names) {
		ArrayList<Integer> depIndexes = new ArrayList<>();
		for (int i = 0; i < names.size(); i++) {
			int tempIndex = names.indexOf(names.get(i));
			if (tempIndex != -1) {
				depIndexes.add(tempIndex);
			} else {
				System.out.println(names.get(i) + " did not have any data associated with the name.");
				names.remove(i);
				i--;
			}
		}
		return depIndexes;
	}
	
	public ArrayList<Double> getData(String name) {
		int index = names.indexOf(name);
		if (index == -1) {
			System.out.println(name + " did not recall any data");
			return null;
		}
		ArrayList<Double> res = new ArrayList<>(data.size());
		for (int i = 0; i < data.size(); i++) {
			res.add(data.get(i)[index]);
		}
		return res;
	}
}
