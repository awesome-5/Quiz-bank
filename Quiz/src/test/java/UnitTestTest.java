
public class UnitTestTest {
	@VaadinUnitTest
	public class BasicUnitTest {

	  @Test
	  void test001(BasicTestPageObject pageObject) {
	    pageObject.loadPage();
	    pageObject.button.get().click();
	  }
	}
}
