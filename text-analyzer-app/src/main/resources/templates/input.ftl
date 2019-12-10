<#import "parts/common.ftl" as c> <@c.page>
<#if !list??>
<div class="form-group mt-3">
	<form method="post" enctype="multipart/form-data">
		<div class="input-group">
			<div class="input-group-prepend">
				<span class="input-group-text">Input text here:</span>
			</div>
			<textarea type="text" name="inputText" class="form-control" aria-label="Input text here:" required></textarea>

		</div>
		<br/>
		<div class="form-group">
			<button type="submit" class="btn btn-info">Analyze</button>
		</div>

	</form>
</div>
</#if>
<#if list??>
<ul class="list-group">
</ul>
<li class="list-group-item active">Text analysis by last word letter (last letter, number of words, words)</li>
<#list list?ifExists as lastLetter>
<li class="list-group-item">${lastLetter.lastLetter} ${lastLetter.wordsNumber}<#list lastLetter.words as word> ${word} </#list></li>
</#list>
</#if>
</@c.page>