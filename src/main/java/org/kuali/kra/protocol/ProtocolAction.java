/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.protocol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.customdata.ProtocolCustomDataAction;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * The ProtocolAction is the base class for all Protocol actions.  Each derived
 * Action class corresponds to one tab (web page).  The derived Action class handles
 * all user requests for that particular tab (web page).
 */
public abstract class ProtocolAction extends KraTransactionalDocumentActionBase {
    
// TODO *********uncomment the code below in increments as needed during refactoring*********     
//    private static final Log LOG = LogFactory.getLog(ProtocolAction.class);
//    private static final String PROTOCOL_NUMBER = "protocolNumber";
//    private static final String SUBMISSION_NUMBER = "submissionNumber";
//    private static final String SUFFIX_T = "T";
//    private static final String NOT_FOUND_SELECTION = "The attachment was not found for selection ";
//    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
// TODO **********************end************************    
    
   
    /** {@inheritDoc} */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        final ActionForward forward = super.execute(mapping, form, request, response);
        if(KNSGlobalVariables.getAuditErrorMap().isEmpty()) {
            new AuditActionHelper().auditConditionally((ProtocolForm) form);
        }
        
        return forward;
    }
    
    
    // TODO invoke these hooks at appropriate points in action methods to get the actual forward name from the subclasses
    protected abstract String getProtocolForwardNameHook();
    protected abstract String getQuestionnaireForwardNameHook();
    protected abstract String getPersonnelForwardNameHook();
    protected abstract String getNoteAndAttachmentForwardNameHook();
    protected abstract String getProtocolActionsForwardNameHook();
    protected abstract String getProtocolOnlineReviewForwardNameHook();
    protected abstract String getProtocolPermissionsForwardNameHook();
    protected abstract String getCustomAttributeMappingHook();
    protected abstract String getSpecialReviewForwardNameHook();
    
    public ActionForward protocol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((ProtocolForm)form).getProtocolHelper().prepareView();
        // hook invocation to get the forward name
        return mapping.findForward(getProtocolForwardNameHook());
    }

    
    
// TODO *********uncomment the code below in increments as needed during refactoring*********    
//    
//    public ActionForward personnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//        getProtocolPersonnelService().selectProtocolUnit(((ProtocolForm) form).getProtocolDocument().getProtocol().getProtocolPersons());
//        getProtocolPersonTrainingService().updatePersonTrained(((ProtocolForm) form).getProtocolDocument().getProtocol().getProtocolPersons());
//        ((ProtocolForm)form).getPersonnelHelper().prepareView();
//        return mapping.findForward("personnel");
//    }
//    
//    public ActionForward permissions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//        ((ProtocolForm)form).getPermissionsHelper().prepareView();
//        return mapping.findForward("permissions");
//    }
//    
//    /**
//     * This method gets called upon navigation to Questionnaire tab.
//     * @param mapping the Action Mapping
//     * @param form the Action Form
//     * @param request the Http Request
//     * @param response Http Response
//     * @return the Action Forward
//     */
//    public ActionForward questionnaire(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//
//        ((ProtocolForm)form).getQuestionnaireHelper().prepareView();
//        ((ProtocolForm)form).getQuestionnaireHelper().setSubmissionActionTypeCode(getSubmitActionType(request));
//        // TODO : if questionnaire is already populated, then don't need to do it
//        if (StringUtils.isBlank(((ProtocolForm)form).getQuestionnaireHelper().getSubmissionActionTypeCode()) || CollectionUtils.isEmpty(((ProtocolForm)form).getQuestionnaireHelper().getAnswerHeaders())) {
//            ((ProtocolForm)form).getQuestionnaireHelper().populateAnswers();
//        } else {
//            ProtocolSubmissionBeanBase submissionBean = getSubmissionBean(form, ((ProtocolForm)form).getQuestionnaireHelper().getSubmissionActionTypeCode());
//            if (CollectionUtils.isEmpty(submissionBean.getAnswerHeaders())) {
//                ((ProtocolForm)form).getQuestionnaireHelper().populateAnswers();
//                submissionBean.setAnswerHeaders(((ProtocolForm)form).getQuestionnaireHelper().getAnswerHeaders());
//            } else {
//                ((ProtocolForm)form).getQuestionnaireHelper().setAnswerHeaders(submissionBean.getAnswerHeaders());
//            }
//        }
//        ((ProtocolForm)form).getQuestionnaireHelper().setQuestionnaireActiveStatuses();
//        return mapping.findForward("questionnaire");
//    }
//    
//    protected ProtocolSubmissionBeanBase getSubmissionBean(ActionForm form,String submissionActionType) {
//        ProtocolSubmissionBeanBase submissionBean;
//        if (ProtocolActionType.NOTIFY_IRB.equals(submissionActionType)) {
//            submissionBean = ((ProtocolForm) form).getActionHelper().getProtocolNotifyIrbBean();
//        } else {
//            submissionBean = ((ProtocolForm) form).getActionHelper().getRequestBean(submissionActionType);
//        }
//        return submissionBean;
//    }
//
//    protected String getSubmitActionType(HttpServletRequest request) {
//        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
//        String actionTypeCode = "";
//        if (StringUtils.isNotBlank(parameterName)) {
//            actionTypeCode = StringUtils.substringBetween(parameterName, ".actionType", ".");
//        }
//
//        return actionTypeCode;
//    }
//
//    /**
//     * This method gets called upon navigation to Notes and attachments tab.
//     * @param mapping the Action Mapping
//     * @param form the Action Form
//     * @param request the Http Request
//     * @param response Http Response
//     * @return the Action Forward
//     */
//    public ActionForward noteAndAttachment(ActionMapping mapping, ActionForm form
//            , HttpServletRequest request, HttpServletResponse response) {        
//        ((ProtocolForm) form).getNotesAttachmentsHelper().prepareView();
//        return mapping.findForward("noteAndAttachment");
//    }
    
    /**
     * This method gets called upon navigation to Special Review tab.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward specialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((ProtocolForm) form).getSpecialReviewHelper().prepareView();
        return mapping.findForward(getSpecialReviewForwardNameHook());
    }
    
//    public ActionForward protocolActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {
//        // for protocol lookup copy link - rice 1.1 need this
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        String command = request.getParameter("command");
//        if (KewApiConstants.DOCSEARCH_COMMAND.equals(command)) {
//            String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
//            Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
//            protocolForm.setDocument(retrievedDocument);
//            request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);        
//       }
//        // make sure current submission is displayed when navigate to action page.
//        protocolForm.getActionHelper().setCurrentSubmissionNumber(-1);
//       ((ProtocolForm)form).getActionHelper().prepareView();
//
//       return mapping.findForward("protocolActions");
//    }
    
    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ((ProtocolForm)form).getProtocolCustomDataHelper().prepareView(((ProtocolForm)form).getProtocolDocument());
        return ProtocolCustomDataAction.staticCustomData(mapping, form, request, response, getCustomAttributeMappingHook());
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#save(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public final ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        ProtocolForm protocolForm = (ProtocolForm) form;
// TODO *********uncomment the code below in increments as needed during refactoring*********         
//        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL, protocolForm.getProtocolDocument().getProtocol());
        AuditActionHelper auditActionHelper = new AuditActionHelper();
        
 //      if (isAuthorized(task)) {
            if ( //!protocolForm.getProtocolDocument().getProtocol().isCorrectionMode() || 
                    auditActionHelper.auditUnconditionally(protocolForm.getDocument())) {
                this.preSave(mapping, form, request, response);
                actionForward = super.save(mapping, form, request, response);
                this.postSave(mapping, form, request, response);
                
                if (KRADConstants.SAVE_METHOD.equals(protocolForm.getMethodToCall()) && protocolForm.isAuditActivated() 
                        && GlobalVariables.getMessageMap().hasNoErrors()) {
                    // hook invocation to get the forward name
                    actionForward = mapping.findForward(getProtocolActionsForwardNameHook());
                }
            }
 //       }

        return actionForward;
    }
    
    /**
     * This method allows logic to be executed before a save, after authorization is confirmed.
     * 
     * @param mapping the Action Mapping
     * @param form the Action Form
     * @param request the Http Request
     * @param response Http Response
     * @throws Exception if bad happens
     */
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //do nothing
    }
    
    /**
     * This method allows logic to be executed after a save, after authorization is confirmed.
     * 
     * @param mapping the Action Mapping
     * @param form the Action Form
     * @param request the Http Request
     * @param response Http Response
     * @throws Exception if bad happens
     */
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //do nothing
    }
   
  
// TODO *********uncomment the code below in increments as needed during refactoring*********     
//    /**
//     * Create the original set of Protocol Users for a new Protocol Document.
//     * The creator the protocol is assigned to the PROTOCOL_AGGREGATOR role.
//     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#initialDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
//     */
//    @Override
//    protected void initialDocumentSave(KualiDocumentFormBase form) throws Exception {
//        
//        // Assign the creator of the protocol the AGGREGATOR role.
//        
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        ProtocolDocument doc = protocolForm.getProtocolDocument();
//        String userId = GlobalVariables.getUserSession().getPrincipalId();
//        KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
//        kraAuthService.addRole(userId, RoleConstants.PROTOCOL_AGGREGATOR, doc.getProtocol());
//        kraAuthService.addRole(userId, RoleConstants.PROTOCOL_APPROVER, doc.getProtocol()); 
//        
//        // Add the users defined in the access control list for the protocol's lead unit
//        
//        Permissionable permissionable = protocolForm.getProtocolDocument().getProtocol();
//        UnitAclLoadService unitAclLoadService = KraServiceLocator.getService(UnitAclLoadService.class);
//        unitAclLoadService.loadUnitAcl(permissionable);
//        
//        sendNotification(protocolForm);
//    }
//    
//    @Override
//    protected ActionForward saveOnClose(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ActionForward forward = super.saveOnClose(mapping, form, request, response);
//        
//        if (GlobalVariables.getMessageMap().hasErrors()) {
//            forward = mapping.findForward(Constants.MAPPING_BASIC);
//        }
//        
//        return forward;
//    }
//
//    /** {@inheritDoc} */
//    @Override
//    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        super.refresh(mapping, form, request, response);
//        
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        ProtocolDocument protocolDocument = protocolForm.getProtocolDocument();
//                     
//        // KNS UI hook for lookup resultset, check to see if we are coming back from a lookup
//        if (Constants.MULTIPLE_VALUE.equals(protocolForm.getRefreshCaller())) {
//            // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
//            // Since URLs have a max length of 2000 chars, field conversions can not be done.
//            String lookupResultsSequenceNumber = protocolForm.getLookupResultsSequenceNumber();
//            
//            if (StringUtils.isNotBlank(lookupResultsSequenceNumber)) {
//                
//                @SuppressWarnings("unchecked")
//                Class<BusinessObject> lookupResultsBOClass = (Class<BusinessObject>) Class.forName(protocolForm.getLookupResultsBOClassName());
//                String userName = GlobalVariables.getUserSession().getPerson().getPrincipalId();
//                LookupResultsService service = KraServiceLocator.getService(LookupResultsService.class);
//                Collection<BusinessObject> selectedBOs
//                    = service.retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass, userName);
//                
//                processMultipleLookupResults(protocolDocument, lookupResultsBOClass, selectedBOs);
//            }
//        }
//        
//        // TODO : hack for rice 11 upgrade
//        // when return from lookup
//        if (StringUtils.isNotBlank(protocolForm.getFormKey())) {
//            protocolForm.setFormKey("");
//        }
//        return mapping.findForward(Constants.MAPPING_BASIC );
//    }
//    
//    /**
//     * This method must be overridden by a derived class if that derived class has a field that requires a 
//     * Lookup that returns multiple values.  The derived class should first check the class of the selected BOs.
//     * Based upon the class, the Protocol can be updated accordingly.  This is necessary since there may be
//     * more than one multi-lookup on a web page.
//     * 
//     * @param protocolDocument the Protocol Document
//     * @param lookupResultsBOClass the class of the BOs that are returned by the Lookup
//     * @param selectedBOs the selected BOs
//     */
//    protected <T extends BusinessObject> void processMultipleLookupResults(ProtocolDocument protocolDocument,
//        Class<T> lookupResultsBOClass, Collection<T> selectedBOs) {
//        // do nothing
//    }
//
//    /**
//     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping,
//     * org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//     */
//    @Override
//    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        ActionForward forward = null;
//        
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        String command = protocolForm.getCommand();
//        String detailId;
//       
//        if (command.startsWith(KewApiConstants.DOCSEARCH_COMMAND+"detailId")) {
//            detailId = command.substring((KewApiConstants.DOCSEARCH_COMMAND+"detailId").length());
//            protocolForm.setDetailId(detailId);
//            viewBatchCorrespondence(mapping, protocolForm, request, response);
//            return RESPONSE_ALREADY_HANDLED;
////            protocolForm.setCommand(KewApiConstants.DOCSEARCH_COMMAND);
////            command = KewApiConstants.DOCSEARCH_COMMAND;
//        }
//        if (KewApiConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
//            String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
//            Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
//            protocolForm.setDocument(retrievedDocument);
//            request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);
//            forward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
//            forward = new ActionForward(forward.getPath()+ "?" + KRADConstants.PARAMETER_DOC_ID + "=" + docIdRequestParameter);  
//        } else if (Constants.MAPPING_PROTOCOL_ACTIONS.equals(command) || Constants.MAPPING_PROTOCOL_ONLINE_REVIEW.equals(command)) {
//            String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
//            Document retrievedDocument = KRADServiceLocatorWeb.getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
//            protocolForm.setDocument(retrievedDocument);
//            request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);
//            loadDocument(protocolForm);
//        } else {
//            forward = super.docHandler(mapping, form, request, response);
//        }
//
//        if (KewApiConstants.INITIATE_COMMAND.equals(protocolForm.getCommand())) {
//            protocolForm.getProtocolDocument().initialize();
//        } else {
//            protocolForm.initialize();
//        }
//        
//        if (Constants.MAPPING_PROTOCOL_ACTIONS.equals(command)) {
//            forward = protocolActions(mapping, protocolForm, request, response);
//        }
//        if (Constants.MAPPING_PROTOCOL_ONLINE_REVIEW.equals(command)) {
//            forward = onlineReview(mapping, protocolForm, request, response);
//        }
//        
//        return forward;
//    }
//
//    /**
//     * Get the Kuali Rule Service.
//     * @return the Kuali Rule Service
//     */
//    @Override
//    protected KualiRuleService getKualiRuleService() {
//        return KraServiceLocator.getService(KualiRuleService.class);
//    }
//    
    /**
     * Use the Kuali Rule Service to apply the rules for the given event.
     * @param event the event to process
     * @return true if success; false if there was a validation error
     */
    protected final boolean applyRules(KualiDocumentEvent event) {
        return getKualiRuleService().applyRules(event);
    }

//    /**
//     * This method is to get protocol personnel training service
//     * @return ProtocolPersonTrainingService
//     */
//    private ProtocolPersonTrainingService getProtocolPersonTrainingService() {
//        return (ProtocolPersonTrainingService)KraServiceLocator.getService("protocolPersonTrainingService");
//    }
//    
//    /**
//     * This method is to get protocol personnel service
//     * @return ProtocolPersonnelService
//     */
//    private ProtocolPersonnelService getProtocolPersonnelService() {
//        return (ProtocolPersonnelService)KraServiceLocator.getService("protocolPersonnelService");
//    }
//    
//    /*
//     * Get the ProtocolOnlineReviewService
//     * @return ProtocolOnlineReviewService
//     */
//    
//    protected ProtocolOnlineReviewService getProtocolOnlineReviewService() {
//        return KraServiceLocator.getService(ProtocolOnlineReviewService.class);
//    }
//    
//    /**
//     * This method gets called upon navigation to Online Review tab.
//     * @param mapping the Action Mapping
//     * @param form the Action Form
//     * @param request the Http Request
//     * @param response Http Response
//     * @return the Action Forward
//     */
//    public ActionForward onlineReview(ActionMapping mapping, ActionForm form
//            , HttpServletRequest request, HttpServletResponse response) {        
//        return mapping.findForward(Constants.MAPPING_PROTOCOL_ONLINE_REVIEW);
//    }
//    
//    
//    /**
//     * 
//     * This method is to print submission questionnaire answer
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */                  
//    public ActionForward printSubmissionQuestionnaireAnswer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        ActionForward forward = mapping.findForward(MAPPING_BASIC);
//        Map<String, Object> reportParameters = new HashMap<String, Object>();
//        AnswerHeader answerHeader = getAnswerHeader(request);
//        // for release 3 : if questionnaire questions has answer, then print answer.
//        reportParameters.put("questionnaireId", answerHeader.getQuestionnaire().getQuestionnaireIdAsInteger());
//        reportParameters.put("template", answerHeader.getQuestionnaire().getTemplate());
//        Protocol protocol;
//        if (CoeusSubModule.PROTOCOL_SUBMISSION.equals(answerHeader.getModuleSubItemCode())) {
//            reportParameters.put(PROTOCOL_NUMBER, answerHeader.getModuleItemKey());
//            reportParameters.put(SUBMISSION_NUMBER, answerHeader.getModuleSubItemKey());
//            protocol = getProtocolFinder().findCurrentProtocolByNumber(getProtocolNumber(answerHeader));
//        } else {
//            Map keyValues= new HashMap();
//            keyValues.put(PROTOCOL_NUMBER, answerHeader.getModuleItemKey());
//            keyValues.put("sequenceNumber", answerHeader.getModuleSubItemKey());
//            protocol = ((List<Protocol>)getBusinessObjectService().findMatching(Protocol.class, keyValues)).get(0);
//        }
//
//        AttachmentDataSource dataStream = getQuestionnairePrintingService().printQuestionnaireAnswer(
//                protocol, reportParameters);
//        if (dataStream.getContent() != null) {
//            streamToResponse(dataStream, response);
//            forward = null;
//        }
//        return forward;
//    }
//    
//    /*
//     * This is to retrieve answer header based on answerheaderid
//     */
//    private AnswerHeader getAnswerHeader(HttpServletRequest request) {
//
//        Map<String, String> fieldValues = new HashMap<String, String>();
//        fieldValues.put("answerHeaderId", Integer.toString(this.getSelectedLine(request)));
//        return  (AnswerHeader)getBusinessObjectService().findByPrimaryKey(AnswerHeader.class, fieldValues);
//    }
//
//    protected QuestionnairePrintingService getQuestionnairePrintingService() {
//        return KraServiceLocator.getService(QuestionnairePrintingService.class);
//    }
//
//    /*
//     * get protocolnumber for answerheader moduleitemkey
//     * a saved but not submitted answer has "T" at the end of protocolnumber
//     */
//    private String getProtocolNumber(AnswerHeader answerHeader) {
//        String protocolNumber = answerHeader.getModuleItemKey();
//        if (protocolNumber.endsWith(SUFFIX_T)) {
//            protocolNumber = protocolNumber.substring(0, protocolNumber.length() - 1);
//        }
//        return protocolNumber;
//    }
//    
//    private ProtocolFinderDao getProtocolFinder() {
//        return KraServiceLocator.getService(ProtocolFinderDao.class);
//    }
//
//    private void viewBatchCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//
//        ProtocolForm protocolForm = (ProtocolForm) form;
//        Map primaryKeys = new HashMap();
//        primaryKeys.put("committeeBatchCorrespondenceDetailId", protocolForm.getDetailId());
//        CommitteeBatchCorrespondenceDetail batchCorrespondenceDetail = (CommitteeBatchCorrespondenceDetail) getBusinessObjectService()
//                .findByPrimaryKey(CommitteeBatchCorrespondenceDetail.class, primaryKeys);
//        primaryKeys.clear();
//        primaryKeys.put("id", batchCorrespondenceDetail.getProtocolCorrespondenceId());
//        ProtocolCorrespondence attachment = (ProtocolCorrespondence) getBusinessObjectService().findByPrimaryKey(
//                ProtocolCorrespondence.class, primaryKeys);
//
//        if (attachment == null) {
//            LOG.info(NOT_FOUND_SELECTION + "detailID: " + protocolForm.getDetailId());
//            // may want to tell the user the selection was invalid.
//            // return mapping.findForward(Constants.MAPPING_BASIC);
//        }
//        else {
//
//            this.streamToResponse(attachment.getCorrespondence(), StringUtils.replace(attachment.getProtocolCorrespondenceType()
//                    .getDescription(), " ", "")
//                    + ".pdf", Constants.PDF_REPORT_CONTENT_TYPE, response);
//        }
//        // return RESPONSE_ALREADY_HANDLED;
//    }
//
//    private void sendNotification(ProtocolForm protocolForm) {
//        
//        Protocol protocol = protocolForm.getProtocolDocument().getProtocol();
//        IRBNotificationRenderer renderer = new IRBNotificationRenderer(protocol);
//        IRBNotificationContext context = new IRBNotificationContext(protocol, ProtocolActionType.PROTOCOL_CREATED_NOTIFICATION, "Created", renderer);
//        KraServiceLocator.getService(KcNotificationService.class).sendNotification(context);
//    }
// TODO **********************end************************  

}