package com.toomanythoughts.tmt.web.logic.communications.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.toomanythoughts.tmt.web.logic.security.authentication.model.EmailVerificationModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.UserModel;
import com.toomanythoughts.tmt.web.logic.security.authorization.security.UserService;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;

@Controller
public class HtmlEmailController {

	@Autowired
	UserService userService;

	@GetMapping("/auth/email")
	public ModelAndView testEmail() {
		final UserEntity user = this.userService.entityByEmail("sergio.weigel@gmail.com");
		final ModelAndView modelAndView = new ModelAndView("registrationMailTemplate");
		modelAndView.addObject("user", this.emailModel(user));
		return modelAndView;
	}

	private EmailVerificationModel emailModel(UserEntity userEntity) {
		final UserModel userModel = this.userService.toModel(userEntity);
		final EmailVerificationModel model = new EmailVerificationModel();
		model.setCredentials(userModel.getCredentials());
		model.setEmailValidationKey("666");
		model.setId(userEntity.getId());
		model.setPersonalData(userModel.getPersonalData());
		model.setPreferredLanguage(userEntity.getPreferredLanguage());
		model.setRoles(userModel.getRoles());
		return model;
	}
}
