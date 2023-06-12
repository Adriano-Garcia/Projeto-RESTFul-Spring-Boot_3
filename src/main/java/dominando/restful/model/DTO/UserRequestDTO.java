package dominando.restful.model.DTO;

import java.io.Serializable;

public class UserRequestDTO implements Serializable{
		private static final long serialVersionUID = 1L;

		private String firstName;
		private String lastName;
		private String email;
		private String password;
		
		
		public UserRequestDTO(String firstName, String lastName, String email, String password) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			return "UserRequestDTO [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
					+ ", password=" + password + "]";
		}
		
		

}
