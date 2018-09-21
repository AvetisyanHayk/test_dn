package com.dn.gaverzicht.renderingstatistics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DocumentTest {
	Document document;
	
	@Before
	public void before() {
		document = new Document(1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_negative_document_id_is_not_allowed() {
		new Document(-1L, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_negative_page_number_is_not_allowed() {
		new Document(1L, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_both_negative_document_id_and_page_number_is_not_allowed() {
		new Document(-1L, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void new_instance_with_document_id_as_zero_is_not_allowed() {
		new Document(0, 1);
	}
	
	@Test
	public void new_instance_with_positive_document_id_and_page_number_as_zero_is_allowed() {
		new Document(1, 0);
	}
	
	@Test
	public void new_instance_with_both_positive_document_id_and_page_number_is_allowed() {
		new Document(1, 1);
	}

	@Test
	public void document_object_equals_to_another_document_object_if_both_have_same_id_and_page_number() {
		assertTrue(document.equals(new Document(1, 0)));
	}
	
	@Test
	public void document_object_not_equal_to_another_document_object_when_having_different_ids_and_same_page_number() {
		assertFalse(document.equals(new Document(2, 1)));
	}
	
	@Test
	public void document_object_not_equal_to_another_document_object_when_having_same_id_and_different_page_numbers() {
		assertFalse(document.equals(new Document(1, 1)));
	}
	
	@Test
	public void document_object_not_equal_to_another_document_object_when_having_different_ids_and_page_numbers() {
		assertFalse(document.equals(new Document(2, 1)));
	}
	
	@Test
	public void hashCodes_of_two_different_document_objects_having_same_id_and_page_number_are_equal() {
		assertTrue(document.hashCode() == (new Document(1, 0).hashCode()));
	}
	
	@Test
	public void hashCodes_of_two_different_document_objects_having_same_id_and_different_page_numbers_are_not_equal() {
		assertFalse(document.hashCode() == (new Document(1, 1).hashCode()));
	}
	
	@Test
	public void hashCodes_of_two_different_document_objects_having_different_ids_and_page_numbers_are_not_equal() {
		assertFalse(document.hashCode() == (new Document(2, 1).hashCode()));
	}
	
	@Test
	public void hashCodes_of_two_different_document_objects_having_different_ids_and_same_page_number_are_not_equal() {
		assertFalse(document.hashCode() == (new Document(2, 0).hashCode()));
	}
}
