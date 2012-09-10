<%--
 Copyright 2005-2010 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<div id="workarea">
    <kul:tab tabTitle="Batch Correspondence"
             tabErrorKey="committeeHelper.generateBatchCorrespondenceTypeCode,committeeHelper.generateStartDate,committeeHelper.generateEndDate,committeeHelper.history*"
             auditCluster="requiredFieldsAuditErrors"  
             defaultOpen="false"
             useCurrentTabIndexAsKey="false"  
             transparentBackground="true">
        <div class="tab-container" align="center">
            <kra-committee:commonCommitteeActionBatchCorrespondenceGenerate />
            <p>&nbsp;</p>
            <kra-committee:commonCommitteeActionBatchCorrespondenceHistory />
            <p>&nbsp;</p>
        </div> 
    </kul:tab>
</div>