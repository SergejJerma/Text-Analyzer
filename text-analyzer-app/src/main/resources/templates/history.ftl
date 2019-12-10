<#import "parts/common.ftl" as c> 
<@c.page>
<ul class="list-group">
</ul>
<li class="list-group-item active">History</li>
<#list historyList?ifExists as text>
<li class="list-group-item"><b>Input text </b><i>(${text.date})</i>: <div>${text.textBody}</div> <br/> <div><b>Analysis:</b></div> <#list text.lastLetters as letter> <div>${letter.lastLetter} ${letter.wordsNumber}<#list letter.words as word> ${word} </#list></div></#list></li>
</#list>

</@c.page>