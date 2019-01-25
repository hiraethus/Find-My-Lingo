package com.clackjones.cymraeg.gwasanaeth.webflow;

import com.clackjones.cymraeg.InvalidUserException;
import com.clackjones.cymraeg.gwasanaeth.Gwasanaeth;
import com.clackjones.cymraeg.gwasanaeth.GwasanaethService;
import com.clackjones.cymraeg.gwasanaeth.ServiceDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.core.collection.ParameterMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

@Component
public class CreateOrRetrieveService extends AbstractAction {
    private final GwasanaethService gwasanaethService;

    @Autowired
    public CreateOrRetrieveService(GwasanaethService gwasanaethService) {
        this.gwasanaethService = gwasanaethService;
    }
    @Override
    public Event doExecute(RequestContext requestContext) throws Exception {

        String flowId = requestContext.getActiveFlow().getId();
        if (flowId.equals("add")) {
            requestContext.getFlowScope().put("aService", new Gwasanaeth());
            return success();
        }

        // is edit
        ParameterMap attribs = requestContext.getRequestParameters();
        Long serviceId = attribs.get("serviceId", Long.class);
        String username = requestContext.getExternalContext().getCurrentUser().getName();

        try {
            Gwasanaeth service = this.gwasanaethService.retrieveService(serviceId, username);
            requestContext.getFlowScope().put("aService", service);

            return success();
        } catch (InvalidUserException|ServiceDoesntExistException e) {
            return error();
        }
    }
}
