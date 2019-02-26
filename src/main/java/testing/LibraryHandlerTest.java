package testing;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LibraryHandlerTest extends AbstractTesting {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    public void addBook() throws Exception {
        String uri = "/book";
        Book book = new Book();
        book.setAuthor("Author0");
        book.setTitle("Title0");
        book.setPages(70);
        String inputJson = super.mapToJson(book);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Book has been added successfully");
    }



    @Test
    public void updateBook() throws Exception {
        String inuri = "/book";
        Book inbook = new Book();
        inbook.setAuthor("Author0");
        inbook.setTitle("Title0");
        inbook.setPages(70);
        String ininputJson = super.mapToJson(inbook);
        MvcResult inmvcResult = mvc.perform(MockMvcRequestBuilders.post(inuri).contentType(MediaType.APPLICATION_JSON_VALUE).content(ininputJson)).andReturn();
        String incontent = inmvcResult.getResponse().getContentAsString();
        assertEquals(incontent, "Book has been added successfully");

        String uri = "/book/0";
        Book book = new Book();
        book.setAuthor("Author1");
        book.setTitle("Title1");
        book.setPages(70);
        String inputJson = super.mapToJson(book);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Book updated successsfully");
    }

    @Test
    public void detailsBook() throws Exception {
        String inuri = "/book";
        Book inbook = new Book();
        inbook.setAuthor("Author0");
        inbook.setTitle("Title0");
        inbook.setPages(70);
        String ininputJson = super.mapToJson(inbook);
        MvcResult inmvcResult = mvc.perform(MockMvcRequestBuilders.post(inuri).contentType(MediaType.APPLICATION_JSON_VALUE).content(ininputJson)).andReturn();
        String incontent = inmvcResult.getResponse().getContentAsString();
        assertEquals(incontent, "Book has been added successfully");

        String uri = "/book/0";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("Author0"));
    }


    @Test
    public void deleteBook() throws Exception {
        String uri = "/book/0";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Book is deleted successsfully");
    }
}
