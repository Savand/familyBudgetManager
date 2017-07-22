package org.andsav.family_budget_manager.web.measflow;

import org.andsav.family_budget_manager.service.FundsFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeansFlowRestController {

  @Autowired
  private FundsFlowService service;

}
