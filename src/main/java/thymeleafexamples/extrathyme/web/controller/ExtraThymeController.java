/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package thymeleafexamples.extrathyme.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import thymeleafexamples.extrathyme.business.entities.Team;
import thymeleafexamples.extrathyme.business.services.TeamService;

@Controller
public class ExtraThymeController {

  @Autowired
  private TeamService teamService;

  public ExtraThymeController() {
    super();
  }

  @ModelAttribute("teams")
  public List<Team> populateTeams() {
    return this.teamService.findAllTeams();
  }

  @RequestMapping({"/", "/extrathyme"})
  public String show() {
    return "extrathyme";
  }

  // Ajax 方法I
  @RequestMapping(value = "/ajaxshow")
  public void ajaxShowTime(HttpServletRequest request, HttpServletResponse response) {
    Date date = new Date();
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    String timestring = "It is " + formatDate.format(date) + ".";

    try {
      response.setContentType("text/xml;charset=UTF-8");
      String str = "<?xml version=\"1.0\" ?> <clock><timestring>" + timestring + "</timestring></clock>";
      response.getWriter().write(str);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return;
  }

  // Ajax 方法II
  @RequestMapping(value = "/ajaxshow", produces = "text/xml;charset=UTF-8")
  @ResponseBody
  public String ajaxShowTime() {
    String timestring = "It is 12:56:59 on Aug 04, 2008.";
    String str = "<?xml version=\"1.0\" ?> <clock><timestring>" + timestring + "</timestring></clock>";
    return str;
  }
}
