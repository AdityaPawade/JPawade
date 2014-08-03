<div id="sidebar">
  <div class="sidebox">
    <form action="Blog" id="searchform" method="get">
      <input type="text" id="s" name="s" value="type and hit enter" onfocus="this.value=''" onblur="this.value='type and hit enter'"/>
    </form>
  </div>
  <div class="sidebox">
    <h3>Popular Posts</h3>
    <ul class="post-list">
      <JPTags:sidePopular>
      <li>
        <h4><a href="$$TARGET$$" title="">$$TITLE$$</a></h4>
        <span class="info">$$DATE$$ | $$COMMSIZE$$ Comments</span>
      </li>
      </JPTags:sidePopular>
    </ul>
  </div>
  <div class="sidebox">
    <h3>Archive</h3>
    <ul class="post-list archive">
    <JPTags:sideArchive>
      <li><a href="Blog?date=$$TARGET$$" title="">$$ARCHIVE$$</a></li>
    </JPTags:sideArchive>
    </ul>
  </div>
  <div class="sidebox">
    <h3>Tags</h3>
    <ul class="tags">
    <JPTags:sideTags>
      <li><a href="Blog?tag=$$TARGET$$" title="">$$TAG$$</a></li>
    </JPTags:sideTags>
    </ul>
  </div>
</div>