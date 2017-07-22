package org.andsav.family_budget_manager.web.user;

import org.andsav.family_budget_manager.model.User;
import org.andsav.family_budget_manager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

  private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

  @Autowired
  private UserService service;

  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public User get(@PathVariable("id") int id) {
    LOG.debug("get " + id);
    return service.get(id);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void delete(@PathVariable("id") int id) {
    LOG.debug("delete " + id);
    service.delete(id);
  }

  @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public void update(@RequestBody User user) {
    LOG.debug("update user  " + user);
    service.update(user);
  }

}
