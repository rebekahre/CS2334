import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

class NonogramCreatorModelTest {

	@Test
	void testConstructor() {
		try {
			new NonogramCreatorModel(0, 1);
			fail();
		} catch (IllegalArgumentException e) {}

		try {
			new NonogramCreatorModel(1, -1);
			fail();
		} catch (IllegalArgumentException e) {}

		int numRows = 5;
		int numCols = 10;
		NonogramCreatorModel model = new NonogramCreatorModel(numRows, numCols);
		assertEquals(numRows, model.getNumRows());
		assertEquals(numCols, model.getNumCols());
		for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
			for (int colIdx = 0; colIdx < numCols; ++colIdx) {
				assertFalse(model.getCell(rowIdx, colIdx));
			}
		}
	}

	@Test
	void testProject() {
		boolean[] cells = {false, false, false, false, false};
		assertEquals(List.of(0), NonogramCreatorModel.project(cells));

		cells[2] = true;
		assertEquals(List.of(1), NonogramCreatorModel.project(cells));

		cells[4] = true;
		assertEquals(List.of(1, 1), NonogramCreatorModel.project(cells));

		cells[0] = true;
		assertEquals(List.of(1, 1, 1), NonogramCreatorModel.project(cells));

		cells[1] = true;
		assertEquals(List.of(3, 1), NonogramCreatorModel.project(cells));

		cells[3] = true;
		assertEquals(List.of(5), NonogramCreatorModel.project(cells));
	}

	@Test
	void testRowColProject() {
		int numRows = 3;
		int numCols = 3;
		NonogramCreatorModel model = new NonogramCreatorModel(numRows, numCols);
		for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
			assertEquals(List.of(0), model.projectRow(rowIdx));
		}
		for (int colIdx = 0; colIdx < numCols; ++colIdx) {
			assertEquals(List.of(0), model.projectCol(colIdx));
		}

		model.setCell(0, 0, true);
		model.setCell(0, 2, true);
		model.setCell(1, 1, true);
		model.setCell(1, 2, true);
		model.setCell(2, 0, true);
		model.setCell(2, 2, true);
		assertEquals(List.of(1, 1), model.projectRow(0));
		assertEquals(List.of(2), model.projectRow(1));
		assertEquals(List.of(1, 1), model.projectRow(2));
		assertEquals(List.of(1, 1), model.projectCol(0));
		assertEquals(List.of(1), model.projectCol(1));
		assertEquals(List.of(3), model.projectCol(2));
	}

	@Test
	void testToString() {
		NonogramCreatorModel model = new NonogramCreatorModel(3, 3);
		model.setCell(0, 0, true);
		model.setCell(0, 2, true);
		model.setCell(1, 1, true);
		model.setCell(1, 2, true);
		model.setCell(2, 0, true);
		model.setCell(2, 2, true);

		String expected = "3 3" + System.lineSeparator() +
				"1 1" + System.lineSeparator() +
				"2" + System.lineSeparator() +
				"1 1" + System.lineSeparator() +
				"1 1" + System.lineSeparator() +
				"1" + System.lineSeparator() +
				"3";
		assertEquals(expected, model.toString());
		
	}
	
}
