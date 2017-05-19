package org.andsav.family_budget_manager.web.user;

import org.andsav.family_budget_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserRestController {

    @Autowired
    private UserService service;

}
