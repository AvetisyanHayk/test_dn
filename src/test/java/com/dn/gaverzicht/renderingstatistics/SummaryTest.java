package com.dn.gaverzicht.renderingstatistics;

import org.junit.Test;

public class SummaryTest {
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_negative_count_parameter_not_allowed() {
		new Summary(-1, 1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_negative_duplicates_parameter_not_allowed() {
		new Summary(1, -1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_negative_unnecessary_parameter_not_allowed() {
		new Summary(1, 1, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_negative_count_and_duplicate_parameters_not_allowed() {
		new Summary(-1, -1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_negative_count_and_unnecessary_parameters_not_allowed() {
		new Summary(-1, 1, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_negative_duplicate_and_unnecessary_parameters_not_allowed() {
		new Summary(1, -1, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_all_negative_parameters_not_allowed() {
		new Summary(1, -1, -1);
	}
	
	@Test
	public void new_instance_with_all_positive_parameters_allowed() {
		new Summary(1, 1, 1);
	}
	
	@Test
	public void new_instance_with_count_parameter_as_zero_allowed() {
		new Summary(0, 1, 1);
	}
	
	@Test
	public void new_instance_with_duplicates_parameter_as_zero_allowed() {
		new Summary(1, 0, 1);
	}
	
	@Test
	public void new_instance_with_unnecessary_parameter_as_zero_allowed() {
		new Summary(1, 1, 0);
	}
	
	@Test
	public void new_instance_with_count_and_duplicates_parameters_as_zero_allowed() {
		new Summary(0, 0, 1);
	}
	
	@Test
	public void new_instance_with_count_and_unnecessary_parameters_as_zero_allowed() {
		new Summary(0, 1, 0);
	}
	
	@Test
	public void new_instance_with_duplicates_and_unnecessary_parameters_as_zero_allowed() {
		new Summary(1, 0, 0);
	}

	@Test
	public void new_instance_with_all_parameters_as_zero_allowed() {
		new Summary(0, 0, 0);
	}
}
