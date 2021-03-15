package com.example.validation.passtvalidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.EnglishSequenceData;
import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.validation.controller.PassController;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Service
public class PasswordConstraintValidator  {
	

	public String passay(String password) {
		
		String res = null;
		List<Rule> rules = new ArrayList<>();

				// at least 8 characters
				rules.add(new LengthRule(8, 15));

				// at least one upper-case character
				rules.add(new CharacterRule(EnglishCharacterData.UpperCase, 1));

				// at least one lower-case character
				rules.add(new CharacterRule(EnglishCharacterData.LowerCase, 1));

				// at least one digit character
				rules.add(new CharacterRule(EnglishCharacterData.Digit, 1));

				// at least one symbol (special character)
				rules.add(	new CharacterRule(EnglishCharacterData.Special, 1));

				// rejects passwords that contain a sequence of >= 3 characters alphabetical
				// (e.g. abcdef)
				rules.add(new IllegalSequenceRule(EnglishSequenceData.USQwerty, 4, true));

				// rejects passwords that contain a sequence of >= 3 characters numerical (e.g.
				// 12345)
				rules.add(new IllegalSequenceRule(EnglishSequenceData.Numerical, 3, true));
				
				PasswordValidator validator = new PasswordValidator(rules);
				PasswordData passwordData = new PasswordData(password);
				RuleResult result = validator.validate(passwordData);
	
		if (result.isValid()) {
			res ="Password validated";
		} else {
			res = "Invalid Password: " + validator.getMessages(result);
		}
		return res;
	
	}

}
