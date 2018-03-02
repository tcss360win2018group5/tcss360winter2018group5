
package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.SystemCoordinator;
import model.User;
import model.Volunteer;

public class SystemCoordinatorTest {
    public SystemCoordinator globalSystemCoordinator;

    @Before
    public void setUp() throws Exception {
        globalSystemCoordinator = new SystemCoordinator();
    }

    // Business Rule:
    // No User can carry out any other user story until they are identified by
    
    // the system
    // (note this is mainly reflected in the UI but signIn(username) is a prereq
    // to any
    // useful features of the UI)
    @Test
    public final void signIn_isVolunteerInSystem_ShouldBeTrue() {
        Volunteer aValidRegisterdVolunteer = new Volunteer("Jane Doe");
        globalSystemCoordinator.addUser(aValidRegisterdVolunteer);
        assertTrue(globalSystemCoordinator
                        .signIn(aValidRegisterdVolunteer.getUsername()) == 0);
    }

    @Test
    public final void signIn_isNotInSystem_ShouldBeFalse() {
        Volunteer newVolunteerThatHasNotSignedUp = new Volunteer("SomeVolunteer");
        assertFalse(globalSystemCoordinator
                        .signIn(newVolunteerThatHasNotSignedUp.getUsername()) == 0);
    }

    // A user must be one of Urban Parks Staff Member, Park Manager, or
    // Volunteer
    @Test
    public final void canAddUser_addNonValidUserType_ShouldBeFalse() {
        class BusinessPartner extends User {
            public BusinessPartner(String theUsername) {
                super(theUsername, 99);
            }

        }
        BusinessPartner invalidUserThatIsABusinessPartner = new BusinessPartner("John");
        assertFalse(globalSystemCoordinator.canAddUser(invalidUserThatIsABusinessPartner));
    }
}
