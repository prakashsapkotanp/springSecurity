package bloodlink.application.bloodlink.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bloodlink.application.bloodlink.config.JwtService;
import bloodlink.application.bloodlink.entity.User;
import bloodlink.application.bloodlink.entity.Role;
import bloodlink.application.bloodlink.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder()
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole())
				.build();
		var savedUser = repository.save(user);

		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().JwtToken(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		/*
		 * 
		 * it authenticate the token if the token is already generated based on the username and password
		 * 
		 */
		authenticationManager.authenticate(
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								request.getEmail(),
								request.getPassword()
								)
						));
		/*
		 * 
		 * if the token is not generated, generates the token
		 * 
		 */
		var user = repository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);

		return AuthenticationResponse.builder().JwtToken(jwtToken).build();

	}

}
