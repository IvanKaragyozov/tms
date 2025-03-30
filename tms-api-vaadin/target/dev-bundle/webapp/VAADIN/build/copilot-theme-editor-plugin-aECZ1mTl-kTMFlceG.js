import{r as v,m as b,n,o as Le,p as c,g as y,l as Pe,f as Me,t as $,v as Oe,u as R,i as X,y as je,X as qe,G as Z,A as He,F as N,C as Ue,N as De,D as Be,J as Fe}from"./indexhtml-xOI1ENRx.js";import{o as We}from"./base-panel-f4d2zbj_-ij2qSboL.js";var j=(t=>(t.disabled="disabled",t.enabled="enabled",t.missing_theme="missing_theme",t))(j||{}),p=(t=>(t.local="local",t.global="global",t))(p||{});function Y(t,e){return`${t}|${e}`}class f{constructor(e){this._properties={},this._metadata=e}get metadata(){return this._metadata}get properties(){return Object.values(this._properties)}getPropertyValue(e,o){return this._properties[Y(e,o)]||null}updatePropertyValue(e,o,a,s){if(!a){delete this._properties[Y(e,o)];return}let r=this.getPropertyValue(e,o);r?(r.value=a,r.modified=s||!1):(r={elementSelector:e,propertyName:o,value:a,modified:s||!1},this._properties[Y(e,o)]=r)}addPropertyValues(e){e.forEach(o=>{this.updatePropertyValue(o.elementSelector,o.propertyName,o.value,o.modified)})}getPropertyValuesForElement(e){return this.properties.filter(o=>o.elementSelector===e)}static combine(...e){if(e.length<2)throw new Error("Must provide at least two themes");const o=new f(e[0].metadata);return e.forEach(a=>o.addPropertyValues(a.properties)),o}static fromServerRules(e,o,a){const s=new f(e);return e.elements.forEach(r=>{const i=M(r,o),l=a.find(d=>d.selector===i.replace(/ > /g,">"));l&&r.properties.forEach(d=>{const u=l.properties[d.propertyName];u&&s.updatePropertyValue(r.selector,d.propertyName,u,!0)})}),s}}function M(t,e){const o=t.selector;if(e.themeScope==="global")return o;if(!e.localClassName)throw new Error("Can not build local scoped selector without instance class name");const a=o.match(/^[\w\d-_]+/),s=a&&a[0];if(!s)throw new Error(`Selector does not start with a tag name: ${o}`);return`${s}.${e.localClassName}${o.substring(s.length,o.length)}`}function Ge(t,e,o,a){const s=M(t,e),r={[o]:a};return o==="border-width"&&(parseInt(a)>0?r["border-style"]="solid":r["border-style"]=""),{selector:s,properties:r}}function Je(t){const e=Object.entries(t.properties).map(([o,a])=>`${o}: ${a};`).join(" ");return`${t.selector} { ${e} }`}const ae={crosshair:N`<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
   <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
   <path d="M4 8v-2a2 2 0 0 1 2 -2h2"></path>
   <path d="M4 16v2a2 2 0 0 0 2 2h2"></path>
   <path d="M16 4h2a2 2 0 0 1 2 2v2"></path>
   <path d="M16 20h2a2 2 0 0 0 2 -2v-2"></path>
   <path d="M9 12l6 0"></path>
   <path d="M12 9l0 6"></path>
</svg>`,square:N`<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="currentColor" stroke-linecap="round" stroke-linejoin="round">
   <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
   <path d="M3 3m0 2a2 2 0 0 1 2 -2h14a2 2 0 0 1 2 2v14a2 2 0 0 1 -2 2h-14a2 2 0 0 1 -2 -2z"></path>
</svg>`,font:N`<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
   <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
   <path d="M4 20l3 0"></path>
   <path d="M14 20l7 0"></path>
   <path d="M6.9 15l6.9 0"></path>
   <path d="M10.2 6.3l5.8 13.7"></path>
   <path d="M5 20l6 -16l2 0l7 16"></path>
</svg>`,undo:N`<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
   <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
   <path d="M9 13l-4 -4l4 -4m-4 4h11a4 4 0 0 1 0 8h-1"></path>
</svg>`,redo:N`<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
   <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
   <path d="M15 13l4 -4l-4 -4m4 4h-11a4 4 0 0 0 0 8h1"></path>
</svg>`,cross:N`<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" stroke-width="3" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round">
   <path stroke="none" d="M0 0h24v24H0z" fill="none"></path>
   <path d="M18 6l-12 12"></path>
   <path d="M6 6l12 12"></path>
</svg>`};let I,ue="";function Ve(t){I||(I=new CSSStyleSheet,document.adoptedStyleSheets=[...document.adoptedStyleSheets,I]),ue+=t.cssText,I.replaceSync(ue)}const Ae=v`
  .editor-row {
    display: flex;
    align-items: baseline;
    padding: var(--theme-editor-section-horizontal-padding);
    gap: 10px;
  }

  .editor-row > .label {
    flex: 0 0 auto;
    width: 120px;
  }

  .editor-row > .editor {
    flex: 1 1 0;
  }
`,me="__vaadin-theme-editor-measure-element",ve=/((::before)|(::after))$/,ge=/::part\(([\w\d_-]+)\)$/;Ve(v`
  .__vaadin-theme-editor-measure-element {
    position: absolute;
    top: 0;
    left: 0;
    visibility: hidden;
  }
`);async function Ke(t){const e=new f(t),o=document.createElement(t.tagName);o.classList.add(me),document.body.append(o),t.setupElement&&await t.setupElement(o);const a={themeScope:p.local,localClassName:me};try{t.elements.forEach(s=>{ye(o,s,a,!0);let r=M(s,a);const i=r.match(ve);r=r.replace(ve,"");const l=r.match(ge),d=r.replace(ge,"");let u=document.querySelector(d);if(u&&l){const k=`[part~="${l[1]}"]`;u=u.shadowRoot.querySelector(k)}if(!u)return;u.style.transition="none";const C=i?i[1]:null,V=getComputedStyle(u,C);s.properties.forEach(k=>{const Ie=V.getPropertyValue(k.propertyName)||k.defaultValue||"";e.updatePropertyValue(s.selector,k.propertyName,Ie)}),ye(o,s,a,!1)})}finally{try{t.cleanupElement&&await t.cleanupElement(o)}finally{o.remove()}}return e}function ye(t,e,o,a){if(e.stateAttribute){if(e.stateElementSelector){const s=M({...e,selector:e.stateElementSelector},o);t=document.querySelector(s)}t&&(a?t.setAttribute(e.stateAttribute,""):t.removeAttribute(e.stateAttribute))}}function be(t){return t.trim()}function Xe(t){const e=t.element;if(!e)return null;const o=e.querySelector("label");if(o&&o.textContent)return be(o.textContent);const a=e.textContent;return a?be(a):null}class Ze{constructor(){this._localClassNameMap=new Map}get stylesheet(){return this.ensureStylesheet(),this._stylesheet}add(e){this.ensureStylesheet(),this._stylesheet.replaceSync(e)}clear(){this.ensureStylesheet(),this._stylesheet.replaceSync("")}previewLocalClassName(e,o){if(!e)return;const a=this._localClassNameMap.get(e);a&&(e.classList.remove(a),e.overlayClass=null),o?(e.classList.add(o),e.overlayClass=o,this._localClassNameMap.set(e,o)):this._localClassNameMap.delete(e)}ensureStylesheet(){this._stylesheet||(this._stylesheet=new CSSStyleSheet,this._stylesheet.replaceSync(""),document.adoptedStyleSheets=[...document.adoptedStyleSheets,this._stylesheet])}}const _=new Ze,h={index:-1,entries:[]};class Ye{constructor(e){this.api=e}get allowUndo(){return h.index>=0}get allowRedo(){return h.index<h.entries.length-1}get allowedActions(){return{allowUndo:this.allowUndo,allowRedo:this.allowRedo}}push(e,o,a){const s={requestId:e,execute:o,rollback:a};if(h.index++,h.entries=h.entries.slice(0,h.index),h.entries.push(s),o)try{o()}catch(r){console.error("Execute history entry failed",r)}return this.allowedActions}async undo(){if(!this.allowUndo)return this.allowedActions;const e=h.entries[h.index];h.index--;try{await this.api.undo(e.requestId),e.rollback&&e.rollback()}catch(o){console.error("Undo failed",o)}return this.allowedActions}async redo(){if(!this.allowRedo)return this.allowedActions;h.index++;const e=h.entries[h.index];try{await this.api.redo(e.requestId),e.execute&&e.execute()}catch(o){console.error("Redo failed",o)}return this.allowedActions}static clear(){h.entries=[],h.index=-1}}var Qe=Object.defineProperty,et=Object.getOwnPropertyDescriptor,x=(t,e,o,a)=>{for(var s=a>1?void 0:a?et(e,o):e,r=t.length-1,i;r>=0;r--)(i=t[r])&&(s=(a?i(e,o,s):i(s))||s);return a&&s&&Qe(e,o,s),s};class tt extends CustomEvent{constructor(e,o,a){super("theme-property-value-change",{bubbles:!0,composed:!0,detail:{element:e,property:o,value:a}})}}class g extends ${constructor(){super(),this.value=""}static get styles(){return[Ae,v`
        :host {
          display: block;
        }

        .editor-row .label .modified {
          display: inline-block;
          width: 6px;
          height: 6px;
          background: orange;
          border-radius: 3px;
          margin-left: 3px;
        }
      `]}update(e){super.update(e),(e.has("propertyMetadata")||e.has("theme"))&&this.updateValueFromTheme()}render(){var e;return n`
      <div class="editor-row">
        <div class="label">
          ${this.propertyMetadata.displayName}
          ${(e=this.propertyValue)!=null&&e.modified?n`<span class="modified"></span>`:null}
        </div>
        <div class="editor">${this.renderEditor()}</div>
      </div>
    `}updateValueFromTheme(){var e;this.propertyValue=this.theme.getPropertyValue(this.elementMetadata.selector,this.propertyMetadata.propertyName),this.value=((e=this.propertyValue)==null?void 0:e.value)||""}dispatchChange(e){this.dispatchEvent(new tt(this.elementMetadata,this.propertyMetadata,e))}}x([c({})],g.prototype,"elementMetadata",2);x([c({})],g.prototype,"propertyMetadata",2);x([c({})],g.prototype,"theme",2);x([b()],g.prototype,"propertyValue",2);x([b()],g.prototype,"value",2);class q{constructor(e){if(this._values=[],this._rawValues={},e){const o=e.propertyName,a=e.presets??[];this._values=(a||[]).map(r=>r.startsWith("--")?`var(${r})`:r);const s=document.createElement("div");s.style.borderStyle="solid",s.style.visibility="hidden",document.body.append(s);try{this._values.forEach(r=>{s.style.setProperty(o,r);const i=getComputedStyle(s);this._rawValues[r]=i.getPropertyValue(o).trim()})}finally{s.remove()}}}get values(){return this._values}get rawValues(){return this._rawValues}tryMapToRawValue(e){return this._rawValues[e]??e}tryMapToPreset(e){return this.findPreset(e)??e}findPreset(e){const o=e&&e.trim();return this.values.find(a=>this._rawValues[a]===o)}}class fe extends CustomEvent{constructor(e){super("change",{detail:{value:e}})}}let H=class extends ${constructor(){super(),this.value="",this.showClearButton=!1}static get styles(){return v`
      :host {
        display: inline-block;
        width: 100%;
        position: relative;
      }

      input {
        width: 100%;
        box-sizing: border-box;
        padding: 0.25rem 0.375rem;
        color: inherit;
        background: rgba(0, 0, 0, 0.2);
        border-radius: 0.25rem;
        border: none;
      }

      button {
        display: none;
        position: absolute;
        right: 4px;
        top: 4px;
        padding: 0;
        line-height: 0;
        border: none;
        background: none;
        color: var(--dev-tools-text-color);
      }

      button svg {
        width: 16px;
        height: 16px;
      }

      button:not(:disabled):hover {
        color: var(--dev-tools-text-color-emphasis);
      }

      :host(.show-clear-button) input {
        padding-right: 20px;
      }

      :host(.show-clear-button) button {
        display: block;
      }
    `}update(t){super.update(t),t.has("showClearButton")&&(this.showClearButton?this.classList.add("show-clear-button"):this.classList.remove("show-clear-button"))}render(){return n`
      <input class="input" .value=${this.value} @change=${this.handleInputChange} />
      <button @click=${this.handleClearClick}>${ae.cross}</button>
    `}handleInputChange(t){const e=t.target;this.dispatchEvent(new fe(e.value))}handleClearClick(){this.dispatchEvent(new fe(""))}};x([c({})],H.prototype,"value",2);x([c({})],H.prototype,"showClearButton",2);H=x([y("copilot-theme-text-input")],H);var ot=Object.defineProperty,st=Object.getOwnPropertyDescriptor,J=(t,e,o,a)=>{for(var s=a>1?void 0:a?st(e,o):e,r=t.length-1,i;r>=0;r--)(i=t[r])&&(s=(a?i(e,o,s):i(s))||s);return a&&s&&ot(e,o,s),s};class at extends CustomEvent{constructor(e){super("class-name-change",{detail:{value:e}})}}let A=class extends ${constructor(){super(),this.editedClassName="",this.invalid=!1}static get styles(){return[Ae,v`
        .editor-row {
          padding-top: 0;
        }

        .editor-row .editor .error {
          display: inline-block;
          color: var(--dev-tools-red-color);
          margin-top: 4px;
        }
      `]}update(t){super.update(t),t.has("className")&&(this.editedClassName=this.className,this.invalid=!1)}render(){return n` <div class="editor-row local-class-name">
      <div class="label">CSS class name</div>
      <div class="editor">
        <copilot-theme-text-input
          type="text"
          .value=${this.editedClassName}
          @change=${this.handleInputChange}></copilot-theme-text-input>
        ${this.invalid?n`<br /><span class="error">Please enter a valid CSS class name</span>`:null}
      </div>
    </div>`}handleInputChange(t){this.editedClassName=t.detail.value;const e=/^-?[_a-zA-Z]+[_a-zA-Z0-9-]*$/;this.invalid=!this.editedClassName.match(e),!this.invalid&&this.editedClassName!==this.className&&this.dispatchEvent(new at(this.editedClassName))}};J([c({})],A.prototype,"className",2);J([b()],A.prototype,"editedClassName",2);J([b()],A.prototype,"invalid",2);A=J([y("copilot-theme-class-name-editor")],A);var rt=Object.defineProperty,it=Object.getOwnPropertyDescriptor,K=(t,e,o,a)=>{for(var s=a>1?void 0:a?it(e,o):e,r=t.length-1,i;r>=0;r--)(i=t[r])&&(s=(a?i(e,o,s):i(s))||s);return a&&s&&rt(e,o,s),s};class nt extends CustomEvent{constructor(e){super("scope-change",{detail:{value:e}})}}Ve(v`
  vaadin-select-overlay[theme~='copilot-theme-scope-selector'] {
    --lumo-primary-color-50pct: rgba(255, 255, 255, 0.5);
    z-index: 100000 !important;
  }

  vaadin-select-overlay[theme~='copilot-theme-scope-selector']::part(overlay) {
    background: #333;
  }

  vaadin-select-overlay[theme~='copilot-theme-scope-selector'] vaadin-item {
    color: rgba(255, 255, 255, 0.8);
  }

  vaadin-select-overlay[theme~='copilot-theme-scope-selector'] vaadin-item::part(content) {
    font-size: 13px;
  }

  vaadin-select-overlay[theme~='copilot-theme-scope-selector'] vaadin-item .title {
    color: rgba(255, 255, 255, 0.95);
    font-weight: bold;
  }

  vaadin-select-overlay[theme~='copilot-theme-scope-selector'] vaadin-item::part(checkmark) {
    margin: 6px;
  }

  vaadin-select-overlay[theme~='copilot-theme-scope-selector'] vaadin-item::part(checkmark)::before {
    color: rgba(255, 255, 255, 0.95);
  }

  vaadin-select-overlay[theme~='copilot-theme-scope-selector'] vaadin-item:hover {
    background: rgba(255, 255, 255, 0.1);
  }
`);let T=class extends ${constructor(){super(),this.value=p.local}static get styles(){return v`
      vaadin-select {
        --lumo-primary-color-50pct: rgba(255, 255, 255, 0.5);
        width: 100px;
      }

      vaadin-select::part(input-field) {
        background: rgba(0, 0, 0, 0.2);
      }

      vaadin-select vaadin-select-value-button,
      vaadin-select::part(toggle-button) {
        color: var(--dev-tools-text-color);
      }

      vaadin-select:hover vaadin-select-value-button,
      vaadin-select:hover::part(toggle-button) {
        color: var(--dev-tools-text-color-emphasis);
      }

      vaadin-select vaadin-select-item {
        font-size: 13px;
      }
    `}update(t){var e;super.update(t),t.has("metadata")&&((e=this.select)==null||e.requestContentUpdate())}render(){return n` <vaadin-select
      theme="small copilot-theme-scope-selector"
      .value=${this.value}
      .renderer=${this.selectRenderer.bind(this)}
      @value-changed=${this.handleValueChange}></vaadin-select>`}selectRenderer(t){var e;const o=((e=this.metadata)==null?void 0:e.displayName)||"Component",a=`${o}s`;Oe(n`
        <vaadin-list-box>
          <vaadin-item value=${p.local} label="Local">
            <span class="title">Local</span>
            <br />
            <span>Edit styles for this ${o}</span>
          </vaadin-item>
          <vaadin-item value=${p.global} label="Global">
            <span class="title">Global</span>
            <br />
            <span>Edit styles for all ${a}</span>
          </vaadin-item>
        </vaadin-list-box>
      `,t)}handleValueChange(t){const e=t.detail.value;e!==this.value&&this.dispatchEvent(new nt(e))}};K([c({})],T.prototype,"value",2);K([c({})],T.prototype,"metadata",2);K([Pe("vaadin-select")],T.prototype,"select",2);T=K([y("copilot-theme-scope-selector")],T);var lt=Object.defineProperty,dt=Object.getOwnPropertyDescriptor,ct=(t,e,o,a)=>{for(var s=a>1?void 0:a?dt(e,o):e,r=t.length-1,i;r>=0;r--)(i=t[r])&&(s=(a?i(e,o,s):i(s))||s);return a&&s&&lt(e,o,s),s};let we=class extends g{static get styles(){return[g.styles,v`
        .editor-row {
          align-items: center;
        }
      `]}handleInputChange(t){const e=t.target.checked?this.propertyMetadata.checkedValue:"";this.dispatchChange(e||"")}renderEditor(){const t=this.value===this.propertyMetadata.checkedValue;return n` <input type="checkbox" .checked=${t} @change=${this.handleInputChange} /> `}};we=ct([y("copilot-theme-checkbox-property-editor")],we);var ht=Object.defineProperty,pt=Object.getOwnPropertyDescriptor,ut=(t,e,o,a)=>{for(var s=a>1?void 0:a?pt(e,o):e,r=t.length-1,i;r>=0;r--)(i=t[r])&&(s=(a?i(e,o,s):i(s))||s);return a&&s&&ht(e,o,s),s};let xe=class extends g{handleInputChange(t){this.dispatchChange(t.detail.value)}renderEditor(){var t;return n`
      <copilot-theme-text-input
        .value=${this.value}
        .showClearButton=${((t=this.propertyValue)==null?void 0:t.modified)||!1}
        @change=${this.handleInputChange}></copilot-theme-text-input>
    `}};xe=ut([y("copilot-theme-text-property-editor")],xe);/**
 * @license
 * Copyright 2018 Google LLC
 * SPDX-License-Identifier: BSD-3-Clause
 */const mt=Ue(class extends De{constructor(t){var e;if(super(t),t.type!==Be.ATTRIBUTE||t.name!=="class"||((e=t.strings)==null?void 0:e.length)>2)throw Error("`classMap()` can only be used in the `class` attribute and must be the only part in the attribute.")}render(t){return" "+Object.keys(t).filter(e=>t[e]).join(" ")+" "}update(t,[e]){var o,a;if(this.it===void 0){this.it=new Set,t.strings!==void 0&&(this.st=new Set(t.strings.join(" ").split(/\s/).filter(r=>r!=="")));for(const r in e)e[r]&&!((o=this.st)!=null&&o.has(r))&&this.it.add(r);return this.render(e)}const s=t.element.classList;for(const r of this.it)r in e||(s.remove(r),this.it.delete(r));for(const r in e){const i=!!e[r];i===this.it.has(r)||(a=this.st)!=null&&a.has(r)||(i?(s.add(r),this.it.add(r)):(s.remove(r),this.it.delete(r)))}return Fe}});var vt=Object.defineProperty,gt=Object.getOwnPropertyDescriptor,le=(t,e,o,a)=>{for(var s=a>1?void 0:a?gt(e,o):e,r=t.length-1,i;r>=0;r--)(i=t[r])&&(s=(a?i(e,o,s):i(s))||s);return a&&s&&vt(e,o,s),s};let U=class extends g{constructor(){super(),this.selectedPresetIndex=-1,this.presets=new q}static get styles(){return[g.styles,v`
        :host {
          --preset-count: 3;
          --slider-bg: #fff;
          --slider-border: #333;
        }

        .editor-row {
          align-items: center;
        }

        .editor-row > .editor {
          display: flex;
          align-items: center;
          gap: 1rem;
        }

        .editor-row .input {
          flex: 0 0 auto;
          width: 80px;
        }

        .slider-wrapper {
          flex: 1 1 0;
          display: flex;
          align-items: center;
          gap: 0.5rem;
        }

        .icon {
          width: 20px;
          height: 20px;
          color: #aaa;
        }

        .icon.prefix > svg {
          transform: scale(0.75);
        }

        .slider {
          flex: 1 1 0;
          -webkit-appearance: none;
          background: linear-gradient(to right, #666, #666 2px, transparent 2px);
          background-size: calc((100% - 13px) / (var(--preset-count) - 1)) 8px;
          background-position: 5px 50%;
          background-repeat: repeat-x;
        }

        .slider::-webkit-slider-runnable-track {
          width: 100%;
          box-sizing: border-box;
          height: 16px;
          background-image: linear-gradient(#666, #666);
          background-size: calc(100% - 12px) 2px;
          background-repeat: no-repeat;
          background-position: 6px 50%;
        }

        .slider::-moz-range-track {
          width: 100%;
          box-sizing: border-box;
          height: 16px;
          background-image: linear-gradient(#666, #666);
          background-size: calc(100% - 12px) 2px;
          background-repeat: no-repeat;
          background-position: 6px 50%;
        }

        .slider::-webkit-slider-thumb {
          -webkit-appearance: none;
          height: 16px;
          width: 16px;
          border: 2px solid var(--slider-border);
          border-radius: 50%;
          background: var(--slider-bg);
          cursor: pointer;
        }

        .slider::-moz-range-thumb {
          height: 16px;
          width: 16px;
          border: 2px solid var(--slider-border);
          border-radius: 50%;
          background: var(--slider-bg);
          cursor: pointer;
        }

        .custom-value {
          opacity: 0.5;
        }

        .custom-value:hover,
        .custom-value:focus-within {
          opacity: 1;
        }

        .custom-value:not(:hover, :focus-within) {
          --slider-bg: #333;
          --slider-border: #666;
        }
      `]}update(t){t.has("propertyMetadata")&&(this.presets=new q(this.propertyMetadata)),super.update(t)}renderEditor(){var t;const e={"slider-wrapper":!0,"custom-value":this.selectedPresetIndex<0},o=this.presets.values.length;return n`
      <div class=${mt(e)}>
        ${null}
        <input
          type="range"
          class="slider"
          style="--preset-count: ${o}"
          step="1"
          min="0"
          .max=${(o-1).toString()}
          .value=${this.selectedPresetIndex}
          @input=${this.handleSliderInput}
          @change=${this.handleSliderChange} />
        ${null}
      </div>
      <copilot-theme-text-input
        class="input"
        .value=${this.value}
        .showClearButton=${((t=this.propertyValue)==null?void 0:t.modified)||!1}
        @change=${this.handleValueChange}></copilot-theme-text-input>
    `}handleSliderInput(t){const e=t.target,o=parseInt(e.value),a=this.presets.values[o];this.selectedPresetIndex=o,this.value=this.presets.rawValues[a]}handleSliderChange(){this.dispatchChange(this.value)}handleValueChange(t){this.value=t.detail.value,this.updateSliderValue(),this.dispatchChange(this.value)}dispatchChange(t){const e=this.presets.tryMapToPreset(t);super.dispatchChange(e)}updateValueFromTheme(){var t;super.updateValueFromTheme(),this.value=this.presets.tryMapToRawValue(((t=this.propertyValue)==null?void 0:t.value)||""),this.updateSliderValue()}updateSliderValue(){const t=this.presets.findPreset(this.value);this.selectedPresetIndex=t?this.presets.values.indexOf(t):-1}};le([b()],U.prototype,"selectedPresetIndex",2);le([b()],U.prototype,"presets",2);U=le([y("copilot-theme-range-property-editor")],U);const O=(t,e=0,o=1)=>t>o?o:t<e?e:t,m=(t,e=0,o=Math.pow(10,e))=>Math.round(o*t)/o,Te=({h:t,s:e,v:o,a})=>{const s=(200-e)*o/100;return{h:m(t),s:m(s>0&&s<200?e*o/100/(s<=100?s:200-s)*100:0),l:m(s/2),a:m(a,2)}},re=t=>{const{h:e,s:o,l:a}=Te(t);return`hsl(${e}, ${o}%, ${a}%)`},Q=t=>{const{h:e,s:o,l:a,a:s}=Te(t);return`hsla(${e}, ${o}%, ${a}%, ${s})`},yt=({h:t,s:e,v:o,a})=>{t=t/360*6,e=e/100,o=o/100;const s=Math.floor(t),r=o*(1-e),i=o*(1-(t-s)*e),l=o*(1-(1-t+s)*e),d=s%6;return{r:m([o,i,r,r,l,o][d]*255),g:m([l,o,o,i,r,r][d]*255),b:m([r,r,l,o,o,i][d]*255),a:m(a,2)}},bt=t=>{const{r:e,g:o,b:a,a:s}=yt(t);return`rgba(${e}, ${o}, ${a}, ${s})`},ft=t=>{const e=/rgba?\(?\s*(-?\d*\.?\d+)(%)?[,\s]+(-?\d*\.?\d+)(%)?[,\s]+(-?\d*\.?\d+)(%)?,?\s*[/\s]*(-?\d*\.?\d+)?(%)?\s*\)?/i.exec(t);return e?wt({r:Number(e[1])/(e[2]?100/255:1),g:Number(e[3])/(e[4]?100/255:1),b:Number(e[5])/(e[6]?100/255:1),a:e[7]===void 0?1:Number(e[7])/(e[8]?100:1)}):{h:0,s:0,v:0,a:1}},wt=({r:t,g:e,b:o,a})=>{const s=Math.max(t,e,o),r=s-Math.min(t,e,o),i=r?s===t?(e-o)/r:s===e?2+(o-t)/r:4+(t-e)/r:0;return{h:m(60*(i<0?i+6:i)),s:m(s?r/s*100:0),v:m(s/255*100),a}},xt=(t,e)=>{if(t===e)return!0;for(const o in t)if(t[o]!==e[o])return!1;return!0},Ct=(t,e)=>t.replace(/\s/g,"")===e.replace(/\s/g,""),Ce={},ze=t=>{let e=Ce[t];return e||(e=document.createElement("template"),e.innerHTML=t,Ce[t]=e),e},de=(t,e,o)=>{t.dispatchEvent(new CustomEvent(e,{bubbles:!0,detail:o}))};let P=!1;const ie=t=>"touches"in t,kt=t=>P&&!ie(t)?!1:(P||(P=ie(t)),!0),ke=(t,e)=>{const o=ie(e)?e.touches[0]:e,a=t.el.getBoundingClientRect();de(t.el,"move",t.getMove({x:O((o.pageX-(a.left+window.pageXOffset))/a.width),y:O((o.pageY-(a.top+window.pageYOffset))/a.height)}))},_t=(t,e)=>{const o=e.keyCode;o>40||t.xy&&o<37||o<33||(e.preventDefault(),de(t.el,"move",t.getMove({x:o===39?.01:o===37?-.01:o===34?.05:o===33?-.05:o===35?1:o===36?-1:0,y:o===40?.01:o===38?-.01:0},!0)))};class ce{constructor(e,o,a,s){const r=ze(`<div role="slider" tabindex="0" part="${o}" ${a}><div part="${o}-pointer"></div></div>`);e.appendChild(r.content.cloneNode(!0));const i=e.querySelector(`[part=${o}]`);i.addEventListener("mousedown",this),i.addEventListener("touchstart",this),i.addEventListener("keydown",this),this.el=i,this.xy=s,this.nodes=[i.firstChild,i]}set dragging(e){const o=e?document.addEventListener:document.removeEventListener;o(P?"touchmove":"mousemove",this),o(P?"touchend":"mouseup",this)}handleEvent(e){switch(e.type){case"mousedown":case"touchstart":if(e.preventDefault(),!kt(e)||!P&&e.button!=0)return;this.el.focus(),ke(this,e),this.dragging=!0;break;case"mousemove":case"touchmove":e.preventDefault(),ke(this,e);break;case"mouseup":case"touchend":this.dragging=!1;break;case"keydown":_t(this,e);break}}style(e){e.forEach((o,a)=>{for(const s in o)this.nodes[a].style.setProperty(s,o[s])})}}class $t extends ce{constructor(e){super(e,"hue",'aria-label="Hue" aria-valuemin="0" aria-valuemax="360"',!1)}update({h:e}){this.h=e,this.style([{left:`${e/360*100}%`,color:re({h:e,s:100,v:100,a:1})}]),this.el.setAttribute("aria-valuenow",`${m(e)}`)}getMove(e,o){return{h:o?O(this.h+e.x*360,0,360):360*e.x}}}class Et extends ce{constructor(e){super(e,"saturation",'aria-label="Color"',!0)}update(e){this.hsva=e,this.style([{top:`${100-e.v}%`,left:`${e.s}%`,color:re(e)},{"background-color":re({h:e.h,s:100,v:100,a:1})}]),this.el.setAttribute("aria-valuetext",`Saturation ${m(e.s)}%, Brightness ${m(e.v)}%`)}getMove(e,o){return{s:o?O(this.hsva.s+e.x*100,0,100):e.x*100,v:o?O(this.hsva.v-e.y*100,0,100):Math.round(100-e.y*100)}}}const St=':host{display:flex;flex-direction:column;position:relative;width:200px;height:200px;user-select:none;-webkit-user-select:none;cursor:default}:host([hidden]){display:none!important}[role=slider]{position:relative;touch-action:none;user-select:none;-webkit-user-select:none;outline:0}[role=slider]:last-child{border-radius:0 0 8px 8px}[part$=pointer]{position:absolute;z-index:1;box-sizing:border-box;width:28px;height:28px;display:flex;place-content:center center;transform:translate(-50%,-50%);background-color:#fff;border:2px solid #fff;border-radius:50%;box-shadow:0 2px 4px rgba(0,0,0,.2)}[part$=pointer]::after{content:"";width:100%;height:100%;border-radius:inherit;background-color:currentColor}[role=slider]:focus [part$=pointer]{transform:translate(-50%,-50%) scale(1.1)}',Nt="[part=hue]{flex:0 0 24px;background:linear-gradient(to right,red 0,#ff0 17%,#0f0 33%,#0ff 50%,#00f 67%,#f0f 83%,red 100%)}[part=hue-pointer]{top:50%;z-index:2}",Pt="[part=saturation]{flex-grow:1;border-color:transparent;border-bottom:12px solid #000;border-radius:8px 8px 0 0;background-image:linear-gradient(to top,#000,transparent),linear-gradient(to right,#fff,rgba(255,255,255,0));box-shadow:inset 0 0 0 1px rgba(0,0,0,.05)}[part=saturation-pointer]{z-index:3}",L=Symbol("same"),ee=Symbol("color"),_e=Symbol("hsva"),te=Symbol("update"),$e=Symbol("parts"),D=Symbol("css"),B=Symbol("sliders");let Mt=class extends HTMLElement{static get observedAttributes(){return["color"]}get[D](){return[St,Nt,Pt]}get[B](){return[Et,$t]}get color(){return this[ee]}set color(t){if(!this[L](t)){const e=this.colorModel.toHsva(t);this[te](e),this[ee]=t}}constructor(){super();const t=ze(`<style>${this[D].join("")}</style>`),e=this.attachShadow({mode:"open"});e.appendChild(t.content.cloneNode(!0)),e.addEventListener("move",this),this[$e]=this[B].map(o=>new o(e))}connectedCallback(){if(this.hasOwnProperty("color")){const t=this.color;delete this.color,this.color=t}else this.color||(this.color=this.colorModel.defaultColor)}attributeChangedCallback(t,e,o){const a=this.colorModel.fromAttr(o);this[L](a)||(this.color=a)}handleEvent(t){const e=this[_e],o={...e,...t.detail};this[te](o);let a;!xt(o,e)&&!this[L](a=this.colorModel.fromHsva(o))&&(this[ee]=a,de(this,"color-changed",{value:a}))}[L](t){return this.color&&this.colorModel.equal(t,this.color)}[te](t){this[_e]=t,this[$e].forEach(e=>e.update(t))}};class Ot extends ce{constructor(e){super(e,"alpha",'aria-label="Alpha" aria-valuemin="0" aria-valuemax="1"',!1)}update(e){this.hsva=e;const o=Q({...e,a:0}),a=Q({...e,a:1}),s=e.a*100;this.style([{left:`${s}%`,color:Q(e)},{"--gradient":`linear-gradient(90deg, ${o}, ${a}`}]);const r=m(s);this.el.setAttribute("aria-valuenow",`${r}`),this.el.setAttribute("aria-valuetext",`${r}%`)}getMove(e,o){return{a:o?O(this.hsva.a+e.x):e.x}}}const Vt=`[part=alpha]{flex:0 0 24px}[part=alpha]::after{display:block;content:"";position:absolute;top:0;left:0;right:0;bottom:0;border-radius:inherit;background-image:var(--gradient);box-shadow:inset 0 0 0 1px rgba(0,0,0,.05)}[part^=alpha]{background-color:#fff;background-image:url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill-opacity=".05"><rect x="8" width="8" height="8"/><rect y="8" width="8" height="8"/></svg>')}[part=alpha-pointer]{top:50%}`;class At extends Mt{get[D](){return[...super[D],Vt]}get[B](){return[...super[B],Ot]}}const Tt={defaultColor:"rgba(0, 0, 0, 1)",toHsva:ft,fromHsva:bt,equal:Ct,fromAttr:t=>t};class zt extends At{get colorModel(){return Tt}}/**
 * @license
 * Copyright (c) 2017 - 2023 Vaadin Ltd.
 * This program is available under Apache License Version 2.0, available at https://vaadin.com/license/
 */function Rt(t){const e=[];for(;t;){if(t.nodeType===Node.DOCUMENT_NODE){e.push(t);break}if(t.nodeType===Node.DOCUMENT_FRAGMENT_NODE){e.push(t),t=t.host;continue}if(t.assignedSlot){t=t.assignedSlot;continue}t=t.parentNode}return e}const oe={start:"top",end:"bottom"},se={start:"left",end:"right"},Ee=new ResizeObserver(t=>{setTimeout(()=>{t.forEach(e=>{e.target.__overlay&&e.target.__overlay._updatePosition()})})}),It=t=>class extends t{static get properties(){return{positionTarget:{type:Object,value:null},horizontalAlign:{type:String,value:"start"},verticalAlign:{type:String,value:"top"},noHorizontalOverlap:{type:Boolean,value:!1},noVerticalOverlap:{type:Boolean,value:!1},requiredVerticalSpace:{type:Number,value:0}}}static get observers(){return["__positionSettingsChanged(horizontalAlign, verticalAlign, noHorizontalOverlap, noVerticalOverlap, requiredVerticalSpace)","__overlayOpenedChanged(opened, positionTarget)"]}constructor(){super(),this.__onScroll=this.__onScroll.bind(this),this._updatePosition=this._updatePosition.bind(this)}connectedCallback(){super.connectedCallback(),this.opened&&this.__addUpdatePositionEventListeners()}disconnectedCallback(){super.disconnectedCallback(),this.__removeUpdatePositionEventListeners()}__addUpdatePositionEventListeners(){window.addEventListener("resize",this._updatePosition),this.__positionTargetAncestorRootNodes=Rt(this.positionTarget),this.__positionTargetAncestorRootNodes.forEach(e=>{e.addEventListener("scroll",this.__onScroll,!0)})}__removeUpdatePositionEventListeners(){window.removeEventListener("resize",this._updatePosition),this.__positionTargetAncestorRootNodes&&(this.__positionTargetAncestorRootNodes.forEach(e=>{e.removeEventListener("scroll",this.__onScroll,!0)}),this.__positionTargetAncestorRootNodes=null)}__overlayOpenedChanged(e,o){if(this.__removeUpdatePositionEventListeners(),o&&(o.__overlay=null,Ee.unobserve(o),e&&(this.__addUpdatePositionEventListeners(),o.__overlay=this,Ee.observe(o))),e){const a=getComputedStyle(this);this.__margins||(this.__margins={},["top","bottom","left","right"].forEach(s=>{this.__margins[s]=parseInt(a[s],10)})),this.setAttribute("dir",a.direction),this._updatePosition(),requestAnimationFrame(()=>this._updatePosition())}}__positionSettingsChanged(){this._updatePosition()}__onScroll(e){this.contains(e.target)||this._updatePosition()}_updatePosition(){if(!this.positionTarget||!this.opened)return;const e=this.positionTarget.getBoundingClientRect(),o=this.__shouldAlignStartVertically(e);this.style.justifyContent=o?"flex-start":"flex-end";const a=this.__isRTL,s=this.__shouldAlignStartHorizontally(e,a),r=!a&&s||a&&!s;this.style.alignItems=r?"flex-start":"flex-end";const i=this.getBoundingClientRect(),l=this.__calculatePositionInOneDimension(e,i,this.noVerticalOverlap,oe,this,o),d=this.__calculatePositionInOneDimension(e,i,this.noHorizontalOverlap,se,this,s);Object.assign(this.style,l,d),this.toggleAttribute("bottom-aligned",!o),this.toggleAttribute("top-aligned",o),this.toggleAttribute("end-aligned",!r),this.toggleAttribute("start-aligned",r)}__shouldAlignStartHorizontally(e,o){const a=Math.max(this.__oldContentWidth||0,this.$.overlay.offsetWidth);this.__oldContentWidth=this.$.overlay.offsetWidth;const s=Math.min(window.innerWidth,document.documentElement.clientWidth),r=!o&&this.horizontalAlign==="start"||o&&this.horizontalAlign==="end";return this.__shouldAlignStart(e,a,s,this.__margins,r,this.noHorizontalOverlap,se)}__shouldAlignStartVertically(e){const o=this.requiredVerticalSpace||Math.max(this.__oldContentHeight||0,this.$.overlay.offsetHeight);this.__oldContentHeight=this.$.overlay.offsetHeight;const a=Math.min(window.innerHeight,document.documentElement.clientHeight),s=this.verticalAlign==="top";return this.__shouldAlignStart(e,o,a,this.__margins,s,this.noVerticalOverlap,oe)}__shouldAlignStart(e,o,a,s,r,i,l){const d=a-e[i?l.end:l.start]-s[l.end],u=e[i?l.start:l.end]-s[l.start],C=r?d:u,V=C>(r?u:d)||C>o;return r===V}__adjustBottomProperty(e,o,a){let s;if(e===o.end){if(o.end===oe.end){const r=Math.min(window.innerHeight,document.documentElement.clientHeight);if(a>r&&this.__oldViewportHeight){const i=this.__oldViewportHeight-r;s=a-i}this.__oldViewportHeight=r}if(o.end===se.end){const r=Math.min(window.innerWidth,document.documentElement.clientWidth);if(a>r&&this.__oldViewportWidth){const i=this.__oldViewportWidth-r;s=a-i}this.__oldViewportWidth=r}}return s}__calculatePositionInOneDimension(e,o,a,s,r,i){const l=i?s.start:s.end,d=i?s.end:s.start,u=parseFloat(r.style[l]||getComputedStyle(r)[l]),C=this.__adjustBottomProperty(l,s,u),V=o[i?s.start:s.end]-e[a===i?s.end:s.start],k=C?`${C}px`:`${u+V*(i?-1:1)}px`;return{[l]:k,[d]:""}}};var Lt=Object.defineProperty,jt=Object.getOwnPropertyDescriptor,E=(t,e,o,a)=>{for(var s=a>1?void 0:a?jt(e,o):e,r=t.length-1,i;r>=0;r--)(i=t[r])&&(s=(a?i(e,o,s):i(s))||s);return a&&s&&Lt(e,o,s),s};class qt extends CustomEvent{constructor(e){super("color-picker-change",{detail:{value:e}})}}const Re=v`
  :host {
    --preview-size: 24px;
    --preview-color: rgba(0, 0, 0, 0);
  }

  .preview {
    --preview-bg-size: calc(var(--preview-size) / 2);
    --preview-bg-pos: calc(var(--preview-size) / 4);

    width: var(--preview-size);
    height: var(--preview-size);
    padding: 0;
    position: relative;
    overflow: hidden;
    background: none;
    border: solid 2px #888;
    border-radius: 4px;
    box-sizing: content-box;
  }

  .preview::before,
  .preview::after {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
  }

  .preview::before {
    content: '';
    background: white;
    background-image: linear-gradient(45deg, #666 25%, transparent 25%),
      linear-gradient(45deg, transparent 75%, #666 75%), linear-gradient(45deg, transparent 75%, #666 75%),
      linear-gradient(45deg, #666 25%, transparent 25%);
    background-size: var(--preview-bg-size) var(--preview-bg-size);
    background-position: 0 0, 0 0, calc(var(--preview-bg-pos) * -1) calc(var(--preview-bg-pos) * -1),
      var(--preview-bg-pos) var(--preview-bg-pos);
  }

  .preview::after {
    content: '';
    background-color: var(--preview-color);
  }
`;let z=class extends ${constructor(){super(...arguments),this.commitValue=!1}static get styles(){return[Re,v`
        #toggle {
          display: block;
        }
      `]}update(t){super.update(t),t.has("value")&&this.overlay&&this.overlay.requestContentUpdate()}firstUpdated(){this.overlay=document.createElement("copilot-color-picker-overlay"),this.overlay.renderer=this.renderOverlayContent.bind(this),this.overlay.owner=this,this.overlay.positionTarget=this.toggle,this.overlay.noVerticalOverlap=!0,this.overlay.addEventListener("vaadin-overlay-escape-press",this.handleOverlayEscape.bind(this)),this.overlay.addEventListener("vaadin-overlay-close",this.handleOverlayClose.bind(this)),this.append(this.overlay)}render(){const t=this.value||"rgba(0, 0, 0, 0)";return n` <button
      id="toggle"
      class="preview"
      style="--preview-color: ${t}"
      @click=${this.open}></button>`}open(){this.commitValue=!1,this.overlay.opened=!0,this.overlay.style.zIndex="1000000";const t=this.overlay.shadowRoot.querySelector('[part="overlay"]');t.style.background="#333"}renderOverlayContent(t){const e=getComputedStyle(this.toggle,"::after").getPropertyValue("background-color");Oe(n` <div>
        <copilot-color-picker-overlay-content
          .value=${e}
          .presets=${this.presets}
          @color-changed=${this.handleColorChange.bind(this)}></copilot-color-picker-overlay-content>
      </div>`,t)}handleColorChange(t){this.commitValue=!0,this.dispatchEvent(new qt(t.detail.value)),t.detail.close&&(this.overlay.opened=!1,this.handleOverlayClose())}handleOverlayEscape(){this.commitValue=!1}handleOverlayClose(){const t=this.commitValue?"color-picker-commit":"color-picker-cancel";this.dispatchEvent(new CustomEvent(t))}};E([c({})],z.prototype,"value",2);E([c({})],z.prototype,"presets",2);E([Pe("#toggle")],z.prototype,"toggle",2);z=E([y("copilot-color-picker")],z);let F=class extends ${static get styles(){return[Re,v`
        :host {
          display: block;
          padding: 12px;
        }

        .picker::part(saturation),
        .picker::part(hue) {
          margin-bottom: 10px;
        }

        .picker::part(hue),
        .picker::part(alpha) {
          flex: 0 0 20px;
        }

        .picker::part(saturation),
        .picker::part(hue),
        .picker::part(alpha) {
          border-radius: 3px;
        }

        .picker::part(saturation-pointer),
        .picker::part(hue-pointer),
        .picker::part(alpha-pointer) {
          width: 20px;
          height: 20px;
        }

        .swatches {
          display: grid;
          grid-template-columns: repeat(6, var(--preview-size));
          grid-column-gap: 10px;
          grid-row-gap: 6px;
          margin-top: 16px;
        }
      `]}render(){return n` <div>
      <copilot-rgba-string-color-picker
        class="picker"
        .color=${this.value}
        @color-changed=${this.handlePickerChange}></copilot-rgba-string-color-picker>
      ${this.renderSwatches()}
    </div>`}renderSwatches(){if(!this.presets||this.presets.length===0)return;const t=this.presets.map(e=>n` <button
        class="preview"
        style="--preview-color: ${e}"
        @click=${()=>this.selectPreset(e)}></button>`);return n` <div class="swatches">${t}</div>`}handlePickerChange(t){this.dispatchEvent(new CustomEvent("color-changed",{detail:{value:t.detail.value}}))}selectPreset(t){this.dispatchEvent(new CustomEvent("color-changed",{detail:{value:t,close:!0}}))}};E([c({})],F.prototype,"value",2);E([c({})],F.prototype,"presets",2);F=E([y("copilot-color-picker-overlay-content")],F);customElements.whenDefined("vaadin-overlay").then(()=>{const t=customElements.get("vaadin-overlay");class e extends It(t){}customElements.define("copilot-color-picker-overlay",e)});customElements.define("copilot-rgba-string-color-picker",zt);var Ht=Object.defineProperty,Ut=Object.getOwnPropertyDescriptor,Dt=(t,e,o,a)=>{for(var s=a>1?void 0:a?Ut(e,o):e,r=t.length-1,i;r>=0;r--)(i=t[r])&&(s=(a?i(e,o,s):i(s))||s);return a&&s&&Ht(e,o,s),s};let Se=class extends g{constructor(){super(...arguments),this.presets=new q}static get styles(){return[g.styles,v`
        .editor-row {
          align-items: center;
        }

        .editor-row > .editor {
          display: flex;
          align-items: center;
          gap: 0.5rem;
        }
      `]}update(t){t.has("propertyMetadata")&&(this.presets=new q(this.propertyMetadata)),super.update(t)}renderEditor(){var t;return n`
      <copilot-color-picker
        .value=${this.value}
        .presets=${this.presets.values}
        @color-picker-change=${this.handleColorPickerChange}
        @color-picker-commit=${this.handleColorPickerCommit}
        @color-picker-cancel=${this.handleColorPickerCancel}></copilot-color-picker>
      <copilot-theme-text-input
        .value=${this.value}
        .showClearButton=${((t=this.propertyValue)==null?void 0:t.modified)||!1}
        @change=${this.handleInputChange}></copilot-theme-text-input>
    `}handleInputChange(t){this.value=t.detail.value,this.dispatchChange(this.value)}handleColorPickerChange(t){this.value=t.detail.value}handleColorPickerCommit(){this.dispatchChange(this.value)}handleColorPickerCancel(){this.updateValueFromTheme()}dispatchChange(t){const e=this.presets.tryMapToPreset(t);super.dispatchChange(e)}updateValueFromTheme(){var t;super.updateValueFromTheme(),this.value=this.presets.tryMapToRawValue(((t=this.propertyValue)==null?void 0:t.value)||"")}};Se=Dt([y("copilot-theme-color-property-editor")],Se);var Bt=Object.defineProperty,Ft=Object.getOwnPropertyDescriptor,he=(t,e,o,a)=>{for(var s=a>1?void 0:a?Ft(e,o):e,r=t.length-1,i;r>=0;r--)(i=t[r])&&(s=(a?i(e,o,s):i(s))||s);return a&&s&&Bt(e,o,s),s};class Wt extends CustomEvent{constructor(e){super("open-css",{detail:{element:e}})}}let W=class extends ${static get styles(){return v`
      .section .header {
        display: flex;
        align-items: baseline;
        justify-content: space-between;
        padding: 0.4rem var(--theme-editor-section-horizontal-padding);
        color: var(--dev-tools-text-color-emphasis);
        background-color: rgba(0, 0, 0, 0.2);
      }

      .section .property-list .property-editor:not(:last-child) {
        border-bottom: solid 1px rgba(0, 0, 0, 0.2);
      }

      .section .header .open-css {
        all: initial;
        font-family: inherit;
        font-size: var(--dev-tools-font-size-small);
        line-height: 1;
        white-space: nowrap;
        background-color: rgba(255, 255, 255, 0.12);
        color: var(--dev-tools-text-color);
        font-weight: 600;
        padding: 0.25rem 0.375rem;
        border-radius: 0.25rem;
      }

      .section .header .open-css:hover {
        color: var(--dev-tools-text-color-emphasis);
      }
    `}render(){const t=this.metadata.elements.map(e=>this.renderSection(e));return n` <div>${t}</div> `}renderSection(t){const e=t.properties.map(o=>this.renderPropertyEditor(t,o));return n`
      <div class="section" data-testid=${t==null?void 0:t.displayName}>
        <div class="header">
          <span> ${t.displayName} </span>
          <button class="open-css" @click=${()=>this.handleOpenCss(t)}>Edit CSS</button>
        </div>
        <div class="property-list">${e}</div>
      </div>
    `}handleOpenCss(t){this.dispatchEvent(new Wt(t))}renderPropertyEditor(t,e){let o;switch(e.editorType){case X.checkbox:o=R`copilot-theme-checkbox-property-editor`;break;case X.range:o=R`copilot-theme-range-property-editor`;break;case X.color:o=R`copilot-theme-color-property-editor`;break;default:o=R`copilot-theme-text-property-editor`}return je` <${o}
          class="property-editor"
          .elementMetadata=${t}
          .propertyMetadata=${e}
          .theme=${this.theme}
          data-testid=${e.propertyName}
        >
        </${o}>`}};he([c({})],W.prototype,"metadata",2);he([c({})],W.prototype,"theme",2);W=he([y("copilot-theme-property-list")],W);var Gt=Object.defineProperty,Jt=Object.getOwnPropertyDescriptor,S=(t,e,o,a)=>{for(var s=a>1?void 0:a?Jt(e,o):e,r=t.length-1,i;r>=0;r--)(i=t[r])&&(s=(a?i(e,o,s):i(s))||s);return a&&s&&Gt(e,o,s),s};let w=class extends qe{constructor(){super(),this.baseTheme=null,this.editedTheme=null,this.expanded=!1,this.themeEditorState=j.enabled,this.effectiveTheme=null}static get styles(){return v`
      :host {
        animation: fade-in var(--dev-tools-transition-duration) ease-in;
        --theme-editor-section-horizontal-padding: 0.75rem;
        display: flex;
        flex-direction: column;
        max-height: 400px;
      }

      .notice {
        padding: var(--theme-editor-section-horizontal-padding);
      }

      .notice a {
        color: var(--dev-tools-text-color-emphasis);
      }

      .hint vaadin-icon {
        color: var(--dev-tools-green-color);
        font-size: var(--lumo-icon-size-m);
      }

      .hint {
        display: flex;
        align-items: center;
        gap: var(--theme-editor-section-horizontal-padding);
      }

      .header {
        flex: 0 0 auto;
        border-bottom: solid 1px rgba(0, 0, 0, 0.2);
      }

      .header .picker-row {
        padding: var(--theme-editor-section-horizontal-padding);
        display: flex;
        gap: 20px;
        align-items: center;
        justify-content: space-between;
      }

      .picker {
        flex: 1 1 0;
        min-width: 0;
        display: flex;
        align-items: center;
      }

      .picker button {
        min-width: 0;
        display: inline-flex;
        align-items: center;
        padding: 0;
        line-height: 20px;
        border: none;
        background: none;
        color: var(--dev-tools-text-color);
      }

      .picker button:not(:disabled):hover {
        color: var(--dev-tools-text-color-emphasis);
      }

      .picker svg,
      .picker .component-type {
        flex: 0 0 auto;
        margin-right: 4px;
      }

      .picker .instance-name {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        color: #e5a2fce5;
      }

      .picker .instance-name-quote {
        color: #e5a2fce5;
      }

      .picker .no-selection {
        font-style: italic;
      }

      .actions {
        display: flex;
        align-items: center;
        gap: 8px;
      }

      .property-list {
        flex: 1 1 auto;
        overflow-y: auto;
      }

      .link-button {
        all: initial;
        font-family: inherit;
        font-size: var(--dev-tools-font-size-small);
        line-height: 1;
        white-space: nowrap;
        color: inherit;
        font-weight: 600;
        text-decoration: underline;
      }

      .link-button:focus,
      .link-button:hover {
        color: var(--dev-tools-text-color-emphasis);
      }

      .icon-button {
        padding: 0;
        line-height: 0;
        border: none;
        background: none;
        color: var(--dev-tools-text-color);
      }

      .icon-button:disabled {
        opacity: 0.5;
      }

      .icon-button:not(:disabled):hover {
        color: var(--dev-tools-text-color-emphasis);
      }
    `}connectedCallback(){super.connectedCallback(),this.reaction(()=>Z.getSelections,()=>{this.refreshPicked(),this.requestUpdate()}),this.refreshPicked()}async refreshPicked(){var t;if(Z.getSelections.length!==1){this.context=null;return}const e=Z.getSelections[0],o=He(e.element),a=e.metadata;if(!a){this.context={component:o,scope:((t=this.context)==null?void 0:t.scope)||p.local},this.baseTheme=null,this.editedTheme=null,this.effectiveTheme=null;return}await this.refreshComponentAndTheme(o,a)}firstUpdated(){this.history=new Ye(this.api),this.historyActions=this.history.allowedActions,this.undoRedoListener=t=>{var e,o;const a=t.key==="Z"||t.key==="z";a&&(t.ctrlKey||t.metaKey)&&t.shiftKey?(e=this.historyActions)!=null&&e.allowRedo&&this.handleRedo():a&&(t.ctrlKey||t.metaKey)&&(o=this.historyActions)!=null&&o.allowUndo&&this.handleUndo()},document.addEventListener("vaadin-theme-updated",()=>{_.clear(),this.refreshTheme()}),document.addEventListener("keydown",this.undoRedoListener),this.dispatchEvent(new CustomEvent("before-open"))}update(t){super.update(t)}disconnectedCallback(){super.disconnectedCallback(),document.removeEventListener("keydown",this.undoRedoListener),this.dispatchEvent(new CustomEvent("after-close"))}render(){var t,e,o;return this.themeEditorState===j.missing_theme?this.renderMissingThemeNotice():n`
      <div class="header">
        <div class="picker-row">
          ${this.renderPicker()}
          <div class="actions">
            ${(t=this.context)!=null&&t.metadata?n` <copilot-theme-scope-selector
                  .value=${this.context.scope}
                  .metadata=${this.context.metadata}
                  @scope-change=${this.handleScopeChange}></copilot-theme-scope-selector>`:null}
            <button
              class="icon-button"
              data-testid="undo"
              ?disabled=${!((e=this.historyActions)!=null&&e.allowUndo)}
              @click=${this.handleUndo}>
              ${ae.undo}
            </button>
            <button
              class="icon-button"
              data-testid="redo"
              ?disabled=${!((o=this.historyActions)!=null&&o.allowRedo)}
              @click=${this.handleRedo}>
              ${ae.redo}
            </button>
          </div>
        </div>
        ${this.renderLocalClassNameEditor()}
      </div>
      ${this.renderPropertyList()}
    `}renderMissingThemeNotice(){return n`
      <div class="notice">
        It looks like you have not set up an application theme yet. Theme editor requires an existing theme to work
        with. Please check our
        <a href="https://vaadin.com/docs/latest/styling/application-theme" target="_blank">documentation</a>
        on how to set up an application theme.
      </div>
    `}renderPropertyList(){if(!this.context)return null;if(!this.context.metadata){const t=this.context.component.element.localName;return n`
        <div class="notice">Styling <code>&lt;${t}&gt;</code> components is not supported at the moment.</div>
      `}if(this.context.scope===p.local&&!this.context.accessible){const t=this.context.metadata.displayName;return n`
        ${this.context.metadata.notAccessibleDescription&&this.context.scope===p.local?n`<div class="notice hint" style="padding-bottom: 0;">
              <vaadin-icon icon="vaadin:lightbulb"></vaadin-icon>
              <div>${this.context.metadata.notAccessibleDescription}</div>
            </div>`:""}
        <div class="notice">
          The selected ${t} cannot be styled locally. Currently, Theme Editor only supports styling
          instances that are assigned to a local variable, like so:
          <pre><code>Button saveButton = new Button("Save");</code></pre>
          If you want to modify the code so that it satisfies this requirement,
          <button class="link-button" @click=${this.handleShowComponent}>click here</button>
          to open it in your IDE. Alternatively you can choose to style all ${t}s by selecting "Global" from
          the scope dropdown above.
        </div>
      `}return n` ${this.context.metadata.description&&this.context.scope===p.local?n`<div class="notice hint">
            <vaadin-icon icon="vaadin:lightbulb"></vaadin-icon>
            <div>${this.context.metadata.description}</div>
          </div>`:""}
      <copilot-theme-property-list
        class="property-list"
        .metadata=${this.context.metadata}
        .theme=${this.effectiveTheme}
        @theme-property-value-change=${this.handlePropertyChange}
        @open-css=${this.handleOpenCss}></copilot-theme-property-list>`}handleShowComponent(){if(!this.context)return;const t=this.context.component,e={nodeId:t.nodeId,uiId:t.uiId,element:t.element};Me.send("showComponentCreateLocation",e)}async handleOpenCss(t){if(!this.context)return;await this.ensureLocalClassName();const e={themeScope:this.context.scope,localClassName:this.context.localClassName},o=M(t.detail.element,e);await this.api.openCss(o)}renderPicker(){var t;let e;if((t=this.context)!=null&&t.metadata){const o=this.context.scope===p.local?this.context.metadata.displayName:`All ${this.context.metadata.displayName}s`,a=n`<span class="component-type">${o}</span>`,s=this.context.scope===p.local?Xe(this.context.component):null,r=s?n` <span class="instance-name-quote">"</span><span class="instance-name">${s}</span
            ><span class="instance-name-quote">"</span>`:null;e=n`${a} ${r}`}else e=n`<span class="no-selection">Pick a single element to get started</span>`;return n` <div class="picker">${e}</div> `}renderLocalClassNameEditor(){var t;const e=((t=this.context)==null?void 0:t.scope)===p.local&&this.context.accessible;if(!this.context||!e)return null;const o=this.context.localClassName||this.context.suggestedClassName;return n` <copilot-theme-class-name-editor
      .className=${o}
      @class-name-change=${this.handleClassNameChange}>
    </copilot-theme-class-name-editor>`}async handleClassNameChange(t){if(!this.context)return;const e=this.context.localClassName,o=t.detail.value;if(e){const a=this.context.component.element;this.context.localClassName=o;const s=await this.api.setLocalClassName(this.context.component,o);this.historyActions=this.history.push(s.requestId,()=>_.previewLocalClassName(a,o),()=>_.previewLocalClassName(a,e))}else this.context={...this.context,suggestedClassName:o}}handleScopeChange(t){this.context&&this.refreshTheme({...this.context,scope:t.detail.value})}async handlePropertyChange(t){if(!this.context||!this.baseTheme||!this.editedTheme)return;const{element:e,property:o,value:a}=t.detail;this.editedTheme.updatePropertyValue(e.selector,o.propertyName,a,!0),this.effectiveTheme=f.combine(this.baseTheme,this.editedTheme),await this.ensureLocalClassName();const s={themeScope:this.context.scope,localClassName:this.context.localClassName},r=Ge(e,s,o.propertyName,a);try{const i=await this.api.setCssRules([r]);this.historyActions=this.history.push(i.requestId);const l=Je(r);_.add(l)}catch(i){console.error("Failed to update property value",i)}}async handleUndo(){this.historyActions=await this.history.undo(),await this.refreshComponentAndTheme()}async handleRedo(){this.historyActions=await this.history.redo(),await this.refreshComponentAndTheme()}async ensureLocalClassName(){if(!this.context||this.context.scope===p.global||this.context.localClassName)return;if(!this.context.localClassName&&!this.context.suggestedClassName)throw new Error("Cannot assign local class name for the component because it does not have a suggested class name");const t=this.context.component.element,e=this.context.suggestedClassName;this.context.localClassName=e;const o=await this.api.setLocalClassName(this.context.component,e);this.historyActions=this.history.push(o.requestId,()=>_.previewLocalClassName(t,e),()=>_.previewLocalClassName(t))}async refreshComponentAndTheme(t,e){var o,a,s;if(t=t||((o=this.context)==null?void 0:o.component),e=e||((a=this.context)==null?void 0:a.metadata),!t||!e)return;const r=await this.api.loadComponentMetadata(t);_.previewLocalClassName(t.element,r.className),await this.refreshTheme({scope:((s=this.context)==null?void 0:s.scope)||p.local,metadata:e,component:t,localClassName:r.className,suggestedClassName:r.suggestedClassName,accessible:r.accessible})}async refreshTheme(t){const e=t||this.context;if(!e||!e.metadata)return;if(e.scope===p.local&&!e.accessible){this.context=e,this.baseTheme=null,this.editedTheme=null,this.effectiveTheme=null;return}let o=new f(e.metadata);if(!(e.scope===p.local&&!e.localClassName)){const s={themeScope:e.scope,localClassName:e.localClassName},r=e.metadata.elements.map(l=>M(l,s)),i=await this.api.loadRules(r);o=f.fromServerRules(e.metadata,s,i.rules)}const a=await Ke(e.metadata);this.context=e,this.baseTheme=a,this.editedTheme=o,this.effectiveTheme=f.combine(a,this.editedTheme)}};S([c({})],w.prototype,"expanded",2);S([c({})],w.prototype,"themeEditorState",2);S([c()],w.prototype,"api",2);S([b()],w.prototype,"historyActions",2);S([b()],w.prototype,"context",2);S([b()],w.prototype,"effectiveTheme",2);w=S([y("copilot-theme-editor")],w);var ne=(t=>(t.state="copilot-theme-editor-state",t.response="copilot-theme-editor-response",t.loadComponentMetadata="copilot-theme-editor-metadata",t.setLocalClassName="copilot-theme-editor-local-class-name",t.setCssRules="copilot-theme-editor-rules",t.loadRules="copilot-theme-editor-load-rules",t.history="copilot-theme-editor-history",t.openCss="copilot-theme-editor-open-css",t))(ne||{});class Kt{constructor(){this.pendingRequests={},this.requestCounter=0}sendRequest(e,o){const a=(this.requestCounter++).toString(),s=o.uiId??this.getGlobalUiId();return new Promise((r,i)=>{Me.send(e,{...o,requestId:a,uiId:s}),this.pendingRequests[a]={resolve:r,reject:i}})}handleResponse(e){const o=this.pendingRequests[e.requestId];if(!o){console.warn("Received response for unknown request");return}delete this.pendingRequests[e.requestId],e.code==="ok"?o.resolve(e):o.reject(e)}loadComponentMetadata(e){return this.sendRequest("copilot-theme-editor-metadata",{nodeId:e.nodeId})}setLocalClassName(e,o){return this.sendRequest("copilot-theme-editor-local-class-name",{nodeId:e.nodeId,className:o})}setCssRules(e){return this.sendRequest("copilot-theme-editor-rules",{rules:e})}loadRules(e){return this.sendRequest("copilot-theme-editor-load-rules",{selectors:e})}undo(e){return this.sendRequest("copilot-theme-editor-history",{undo:e})}redo(e){return this.sendRequest("copilot-theme-editor-history",{redo:e})}openCss(e){return this.sendRequest("copilot-theme-editor-open-css",{selector:e})}getGlobalUiId(){if(this.globalUiId===void 0){const e=window.Vaadin;if(e&&e.Flow){const{clients:o}=e.Flow,a=Object.keys(o);for(const s of a){const r=o[s];if(r.getNodeId){this.globalUiId=r.getUIId();break}}}}return this.globalUiId??-1}}var Xt=Object.defineProperty,Zt=Object.getOwnPropertyDescriptor,pe=(t,e,o,a)=>{for(var s=a>1?void 0:a?Zt(e,o):e,r=t.length-1,i;r>=0;r--)(i=t[r])&&(s=(a?i(e,o,s):i(s))||s);return a&&s&&Xt(e,o,s),s};const Ne=window.Vaadin.devTools;let G=class extends We{constructor(){super(),this.api=new Kt,this.handleStateEvent=t=>{this.themeEditorState=t.data.state},this.handleServerEvent=t=>{this.api.handleResponse(t.data)},this.expanded=!0,this.themeEditorState=j.disabled}connectedCallback(){super.connectedCallback(),this.onCommand(ne.state,this.handleStateEvent),this.onCommand(ne.response,this.handleServerEvent)}render(){return n` <copilot-theme-editor
      .expanded=${this.expanded}
      .themeEditorState=${this.themeEditorState}
      .api=${this.api}
      @before-open=${this.disableJavaLiveReload}
      @after-close=${this.enableJavaLiveReload}></copilot-theme-editor>`}disableJavaLiveReload(){var t;(t=Ne.javaConnection)==null||t.setActive(!1)}enableJavaLiveReload(){var t;(t=Ne.javaConnection)==null||t.setActive(!0)}};pe([b()],G.prototype,"expanded",2);pe([b()],G.prototype,"themeEditorState",2);G=pe([y("copilot-theme-editor-panel")],G);const Yt={header:"Theme Editor",expanded:!0,draggable:!0,panelOrder:0,panel:"right",floating:!1,tag:"copilot-theme-editor-panel",showOn:[Le.Flow]},Qt={init(t){t.addPanel(Yt)}};window.Vaadin.copilotPlugins.push(Qt);export{G as CopilotThemeEditorPanel};
