package org.andsav.family_budget_manager.web.budget;

import org.andsav.family_budget_manager.service.BudgetService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BudgetRestController {

    private static final Log LOG = LogFactory.getLog(BudgetRestController.class);

    @Autowired
    private BudgetService budgetService;



}
