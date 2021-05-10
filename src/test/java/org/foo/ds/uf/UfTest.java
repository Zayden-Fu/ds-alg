package org.foo.ds.uf;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * User: fuzongyang
 * Date: 2021/5/5
 * Time: 10:30 PM
 */
public class UfTest {

	@Test
	void test_weighted_quick_union() throws IOException {
		Path path = Paths.get("./build/resources/main","alg4-data/mediumUF.txt");
		List<String> lines = Files.readAllLines(path);
		int N = Integer.parseInt(lines.get(0).trim());
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
		for (int i = 1; i < lines.size(); i++) {
			String[] lineArr = lines.get(i).split(" ");
			int p = Integer.parseInt(lineArr[0]);
			int q = Integer.parseInt(lineArr[1]);
			uf.union(p, q);
			System.out.println(p + " " + q);
		}
		System.out.println(uf.count() + "components");
	}

}
