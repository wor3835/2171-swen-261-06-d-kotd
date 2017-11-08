package com.webcheckers.ui;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import spark.ModelAndView;

/**
 * Helper class to extract data from Spark's {@link ModelAndView} objects.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class MyModelAndView {

  /**
   *
   * @param myModelView
   * @return
   */
  static Answer<Object> makeAnswer(final MyModelAndView myModelView) {
    return new Answer<Object>() {
      @Override
      public Object answer(InvocationOnMock invocation) throws Throwable {
        final ModelAndView modelAndView = invocation.getArgument(0);
        myModelView.model = modelAndView.getModel();
        myModelView.viewName = modelAndView.getViewName();
        // the return value is not of interest
        return null;
      }
    };
  }

  Object model;
  String viewName;
}
