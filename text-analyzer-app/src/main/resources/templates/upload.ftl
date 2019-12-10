<#import "parts/common.ftl" as c> <@c.page> 
<#if !list??>


<div>
	<form method="post" enctype="multipart/form-data">

		<div class="input-group mb-3">
			<div class="custom-file">
				<input type="file" name="file" class="custom-file-input"
					id="inputGroupFile01" required> <label class="custom-file-label"
					for="inputGroupFile01">Choose file</label>
			</div>
			<div class="input-group-append">
				<button class="btn btn-primary">Upload</button>
			</div>
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
<script>
            $('#inputGroupFile01').on('change',function(){
                //get the file name
                var fileName = $(this).val();
                //replace the "Choose a file" label
                $(this).next('.custom-file-label').html(fileName);
            })
        </script>
</@c.page>