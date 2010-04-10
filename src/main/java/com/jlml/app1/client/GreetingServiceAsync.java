package com.jlml.app1.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.jlml.app1.shared.ProfileVO;

import java.util.ArrayList;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

  void greetServer(String input, AsyncCallback<String> callback)
      throws IllegalArgumentException;

  void greetServer2(String input, AsyncCallback<ArrayList<ProfileVO>> callback);
          
}
