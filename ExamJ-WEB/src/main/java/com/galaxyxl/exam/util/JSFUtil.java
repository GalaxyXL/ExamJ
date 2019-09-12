package com.galaxyxl.exam.util;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;

public class JSFUtil {

    public static UIComponent findComponentById(FacesContext context, final String componentId) {

        UIViewRoot root = context.getViewRoot();
        final UIComponent[] found = new UIComponent[1];

        VisitContext visitContext = VisitContext.createVisitContext(context);
        /*root.visitTree(visitContext, new VisitCallback() {
                    @Override
                    public VisitResult visit(VisitContext context, UIComponent component) {
                        if (component.getId().equals(componentId)) {
                            found[0] = component;
                            return VisitResult.COMPLETE;
                        }
                        return VisitResult.ACCEPT;
                    }
                }
        );*/

        //Using lambda expression simplify code
        root.visitTree(visitContext, (ctx, component) -> {
                    if (component.getId().equals(componentId)) {
                        found[0] = component;
                        return VisitResult.COMPLETE;
                    }
                    return VisitResult.ACCEPT;
                }
        );
        return found[0];
    }

}
