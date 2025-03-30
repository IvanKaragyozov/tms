import{n as v,a as b,m as C,b as h,g as w}from"./indexhtml-xOI1ENRx.js";import{o as A}from"./base-panel-f4d2zbj_-ij2qSboL.js";var I=function(){var e=document.getSelection();if(!e.rangeCount)return function(){};for(var t=document.activeElement,o=[],s=0;s<e.rangeCount;s++)o.push(e.getRangeAt(s));switch(t.tagName.toUpperCase()){case"INPUT":case"TEXTAREA":t.blur();break;default:t=null;break}return e.removeAllRanges(),function(){e.type==="Caret"&&e.removeAllRanges(),e.rangeCount||o.forEach(function(r){e.addRange(r)}),t&&t.focus()}},E=I,m={"text/plain":"Text","text/html":"Url",default:"Text"},S="Copy to clipboard: #{key}, Enter";function R(e){var t=(/mac os x/i.test(navigator.userAgent)?"⌘":"Ctrl")+"+C";return e.replace(/#{\s*key\s*}/g,t)}function x(e,t){var o,s,r,c,l,a,p=!1;t||(t={}),o=t.debug||!1;try{r=E(),c=document.createRange(),l=document.getSelection(),a=document.createElement("span"),a.textContent=e,a.ariaHidden="true",a.style.all="unset",a.style.position="fixed",a.style.top=0,a.style.clip="rect(0, 0, 0, 0)",a.style.whiteSpace="pre",a.style.webkitUserSelect="text",a.style.MozUserSelect="text",a.style.msUserSelect="text",a.style.userSelect="text",a.addEventListener("copy",function(i){if(i.stopPropagation(),t.format)if(i.preventDefault(),typeof i.clipboardData>"u"){o&&console.warn("unable to use e.clipboardData"),o&&console.warn("trying IE specific stuff"),window.clipboardData.clearData();var u=m[t.format]||m.default;window.clipboardData.setData(u,e)}else i.clipboardData.clearData(),i.clipboardData.setData(t.format,e);t.onCopy&&(i.preventDefault(),t.onCopy(i.clipboardData))}),document.body.appendChild(a),c.selectNodeContents(a),l.addRange(c);var y=document.execCommand("copy");if(!y)throw new Error("copy command was unsuccessful");p=!0}catch(i){o&&console.error("unable to copy using execCommand: ",i),o&&console.warn("trying IE specific stuff");try{window.clipboardData.setData(t.format||"text",e),t.onCopy&&t.onCopy(window.clipboardData),p=!0}catch(u){o&&console.error("unable to copy using clipboardData: ",u),o&&console.error("falling back to prompt"),s=R("message"in t?t.message:S),window.prompt(s,e)}}finally{l&&(typeof l.removeRange=="function"?l.removeRange(c):l.removeAllRanges()),a&&document.body.removeChild(a),r()}return p}var D=x;const k=h(D);var d=(e=>(e.ACTIVE="active",e.INACTIVE="inactive",e.UNAVAILABLE="unavailable",e.ERROR="error",e))(d||{}),$=Object.defineProperty,T=Object.getOwnPropertyDescriptor,g=(e,t,o,s)=>{for(var r=s>1?void 0:s?T(t,o):t,c=e.length-1,l;c>=0;c--)(l=e[c])&&(r=(s?l(t,o,r):l(r))||r);return s&&r&&$(t,o,r),r};const n=window.Vaadin.devTools;let f=class extends A{constructor(){super(),this.handleServerInfoEvent=e=>{this.serverInfo=e.data},this.serverInfo={versions:[]}}connectedCallback(){super.connectedCallback(),this.onCommand("serverInfo",this.handleServerInfoEvent)}render(){return v` <div class="info-tray">
      <button class="button copy" @click=${this.copyInfoToClipboard}>Copy</button>
      <dl>
        ${this.serverInfo.versions.map(e=>v`
            <dt>${e.name}</dt>
            <dd>${e.version}</dd>
          `)}
        <dt>Browser</dt>
        <dd>${navigator.userAgent}</dd>
        <dt>
          Live reload
          <label class="switch">
            <input
              id="toggle"
              type="checkbox"
              ?disabled=${!n.conf.enable||(n.frontendStatus===d.UNAVAILABLE||n.frontendStatus===d.ERROR)&&(n.javaStatus===d.UNAVAILABLE||n.javaStatus===d.ERROR)}
              ?checked="${n.frontendStatus===d.ACTIVE||n.javaStatus===d.ACTIVE}"
              @change=${e=>n.setActive(e.target.checked)} />
            <span class="slider"></span>
          </label>
        </dt>
        <dd class="live-reload-status" style="--status-color: ${n.getStatusColor(n.javaStatus)}">
          Java ${n.javaStatus} ${n.conf.backend?`(${n.conf.backend})`:""}
        </dd>
        <dd class="live-reload-status" style="--status-color: ${n.getStatusColor(n.frontendStatus)}">
          Front end ${n.frontendStatus}
        </dd>
      </dl>
    </div>`}copyInfoToClipboard(){const e=this.renderRoot.querySelectorAll(".info-tray dt, .info-tray dd"),t=Array.from(e).map(o=>(o.localName==="dd"?": ":`
`)+o.textContent.trim()).join("").replace(/^\n/,"");k(t),n.showNotification(b.INFORMATION,"Environment information copied to clipboard",void 0,void 0,"versionInfoCopied")}};g([C()],f.prototype,"serverInfo",2);f=g([w("copilot-info-panel")],f);const N={header:"Info",expanded:!0,draggable:!0,panelOrder:0,panel:"right",floating:!1,tag:"copilot-info-panel"},O={init(e){e.addPanel(N)}};window.Vaadin.copilotPlugins.push(O);export{f as CopilotInfoPanel};
