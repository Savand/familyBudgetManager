package org.andsav.family_budget_manager.web.budget;

import org.andsav.family_budget_manager.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BudgetRestController {

    @Autowired
    private BudgetService service;



}
