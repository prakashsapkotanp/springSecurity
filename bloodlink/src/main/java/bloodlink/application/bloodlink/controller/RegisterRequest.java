package bloodlink.application.bloodlink.controller;

import javax.management.relation.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	private String firstname;
	private String lastname;
	private String email;
	private String password;

	public Role getRole() {
		// TODO Auto-generated method stub
		return null;
	}


}
