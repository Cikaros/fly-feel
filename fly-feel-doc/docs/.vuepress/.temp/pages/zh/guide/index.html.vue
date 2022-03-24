<template><h1 id="指南" tabindex="-1"><a class="header-anchor" href="#指南" aria-hidden="true">#</a> 指南</h1>
<h2 id="介绍" tabindex="-1"><a class="header-anchor" href="#介绍" aria-hidden="true">#</a> 介绍</h2>
<p>为快速搭建开发环境所做的框架，内部涵盖大部分情况下的解决方案。</p>
<p>主要有以下特点：</p>
<ul>
<li>XSS防护</li>
<li>跨域处理</li>
<li>提供基础的业务支撑</li>
<li>完备的权限管理体系</li>
<li>基于Oauth2的权限服务与资源服务</li>
<li>验证码</li>
</ul>
<h3 id="模块介绍" tabindex="-1"><a class="header-anchor" href="#模块介绍" aria-hidden="true">#</a> 模块介绍</h3>
<p>目前主要有以下可使用的模块</p>
<ul>
<li>fly-feel-dependencies 版本管理</li>
<li>fly-feel-core 核心</li>
<li>fly-feel-define 基础业务定义</li>
<li>fly-feel-define-impl 基础业务实现</li>
<li>fly-feel-test 测试</li>
<li>fly-feel-verify 验证码</li>
<li>fly-feel-security 安全</li>
</ul>
<h3 id="fly-feel-dependencies" tabindex="-1"><a class="header-anchor" href="#fly-feel-dependencies" aria-hidden="true">#</a> fly-feel-dependencies</h3>
<p>为方便开发者管理项目依赖专门实现的统一的依赖管理 <em>Pom</em>。</p>
<h3 id="fly-feel-core" tabindex="-1"><a class="header-anchor" href="#fly-feel-core" aria-hidden="true">#</a> fly-feel-core</h3>
<p>fly-feel项目基础核心部件，内部提供了最基础的一些规范和部件。</p>
<h3 id="fly-feel-define" tabindex="-1"><a class="header-anchor" href="#fly-feel-define" aria-hidden="true">#</a> fly-feel-define</h3>
<p>定义了基础的框架实体和业务接口。</p>
<h3 id="fly-feel-define-impl" tabindex="-1"><a class="header-anchor" href="#fly-feel-define-impl" aria-hidden="true">#</a> fly-feel-define-impl</h3>
<p>对业务接口的相应实现。</p>
<h3 id="fly-feel-test" tabindex="-1"><a class="header-anchor" href="#fly-feel-test" aria-hidden="true">#</a> fly-feel-test</h3>
<p>提供了默认测试父类，并提供了数据生成工具 <em>podam</em>。</p>
<h3 id="fly-feel-verify" tabindex="-1"><a class="header-anchor" href="#fly-feel-verify" aria-hidden="true">#</a> fly-feel-verify</h3>
<p>提供常用的验证码生成。</p>
<h3 id="fly-feel-security" tabindex="-1"><a class="header-anchor" href="#fly-feel-security" aria-hidden="true">#</a> fly-feel-security</h3>
<p>提供了最基础的安全机制，包括xss,cors,授权服务器和资源服务器等。</p>
<h2 id="快速上手" tabindex="-1"><a class="header-anchor" href="#快速上手" aria-hidden="true">#</a> 快速上手</h2>
<p>首先创建一个maven项目，并在 <code>pom.xml</code> 中加入以下代码。</p>
<div class="language-xml ext-xml line-numbers-mode"><pre v-pre class="language-xml"><code>
<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>project</span><span class="token punctuation">></span></span>
    <span class="token comment">&lt;!--忽略上述标签--></span>

    <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>dependencyManagement</span><span class="token punctuation">></span></span>
        <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>dependencies</span><span class="token punctuation">></span></span>
            <span class="token comment">&lt;!--版本控制--></span>
            <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>dependency</span><span class="token punctuation">></span></span>
                <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>groupId</span><span class="token punctuation">></span></span>io.gitee.cikaros<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>groupId</span><span class="token punctuation">></span></span>
                <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>artifactId</span><span class="token punctuation">></span></span>fly-feel-dependencies<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>artifactId</span><span class="token punctuation">></span></span>
                <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>version</span><span class="token punctuation">></span></span>2.0.2<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>version</span><span class="token punctuation">></span></span>
                <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>type</span><span class="token punctuation">></span></span>pom<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>type</span><span class="token punctuation">></span></span>
                <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>scope</span><span class="token punctuation">></span></span>import<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>scope</span><span class="token punctuation">></span></span>
            <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>dependency</span><span class="token punctuation">></span></span>
        <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>dependencies</span><span class="token punctuation">></span></span>
    <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>dependencyManagement</span><span class="token punctuation">></span></span>

    <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>dependencies</span><span class="token punctuation">></span></span>
        <span class="token comment">&lt;!--fly-feel核心--></span>
        <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>dependency</span><span class="token punctuation">></span></span>
            <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>groupId</span><span class="token punctuation">></span></span>io.gitee.cikaros<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>groupId</span><span class="token punctuation">></span></span>
            <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;</span>artifactId</span><span class="token punctuation">></span></span>fly-feel-core<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>artifactId</span><span class="token punctuation">></span></span>
        <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>dependency</span><span class="token punctuation">></span></span>
    <span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>dependencies</span><span class="token punctuation">></span></span>

    <span class="token comment">&lt;!--忽略下述标签--></span>
<span class="token tag"><span class="token tag"><span class="token punctuation">&lt;/</span>project</span><span class="token punctuation">></span></span>
</code></pre><div class="line-numbers" aria-hidden="true"><span class="line-number">1</span><br><span class="line-number">2</span><br><span class="line-number">3</span><br><span class="line-number">4</span><br><span class="line-number">5</span><br><span class="line-number">6</span><br><span class="line-number">7</span><br><span class="line-number">8</span><br><span class="line-number">9</span><br><span class="line-number">10</span><br><span class="line-number">11</span><br><span class="line-number">12</span><br><span class="line-number">13</span><br><span class="line-number">14</span><br><span class="line-number">15</span><br><span class="line-number">16</span><br><span class="line-number">17</span><br><span class="line-number">18</span><br><span class="line-number">19</span><br><span class="line-number">20</span><br><span class="line-number">21</span><br><span class="line-number">22</span><br><span class="line-number">23</span><br><span class="line-number">24</span><br><span class="line-number">25</span><br><span class="line-number">26</span><br><span class="line-number">27</span><br></div></div><p>创建一个程序入口类，并写入以下代码：</p>
<div class="language-java ext-java line-numbers-mode"><pre v-pre class="language-java"><code><span class="token keyword">import</span> <span class="token namespace">org<span class="token punctuation">.</span>springframework<span class="token punctuation">.</span>boot<span class="token punctuation">.</span></span><span class="token class-name">SpringApplication</span><span class="token punctuation">;</span>
<span class="token keyword">import</span> <span class="token namespace">org<span class="token punctuation">.</span>springframework<span class="token punctuation">.</span>boot<span class="token punctuation">.</span>autoconfigure<span class="token punctuation">.</span></span><span class="token class-name">SpringBootApplication</span><span class="token punctuation">;</span>

<span class="token doc-comment comment">/**
 * 基础服务器
 *
 * <span class="token keyword">@author</span> Cikaros
 * <span class="token keyword">@date</span> 2022/3/22
 * <span class="token keyword">@since</span> v1.0
 */</span>
<span class="token annotation punctuation">@SpringBootApplication</span>
<span class="token keyword">public</span> <span class="token keyword">class</span> <span class="token class-name">FlyFeelSimpleServer</span> <span class="token punctuation">{</span>

    <span class="token keyword">public</span> <span class="token keyword">static</span> <span class="token keyword">void</span> <span class="token function">main</span><span class="token punctuation">(</span><span class="token class-name">String</span><span class="token punctuation">[</span><span class="token punctuation">]</span> args<span class="token punctuation">)</span> <span class="token punctuation">{</span>
        <span class="token class-name">SpringApplication</span><span class="token punctuation">.</span><span class="token function">run</span><span class="token punctuation">(</span><span class="token class-name">FlyFeelSimpleServer</span><span class="token punctuation">.</span><span class="token keyword">class</span><span class="token punctuation">,</span> args<span class="token punctuation">)</span><span class="token punctuation">;</span>
    <span class="token punctuation">}</span>

<span class="token punctuation">}</span>
</code></pre><div class="line-numbers" aria-hidden="true"><span class="line-number">1</span><br><span class="line-number">2</span><br><span class="line-number">3</span><br><span class="line-number">4</span><br><span class="line-number">5</span><br><span class="line-number">6</span><br><span class="line-number">7</span><br><span class="line-number">8</span><br><span class="line-number">9</span><br><span class="line-number">10</span><br><span class="line-number">11</span><br><span class="line-number">12</span><br><span class="line-number">13</span><br><span class="line-number">14</span><br><span class="line-number">15</span><br><span class="line-number">16</span><br><span class="line-number">17</span><br><span class="line-number">18</span><br></div></div><p>在<code>resources</code>中新建<code>application.yml</code>文件，并在内部书写以下配置：</p>
<div class="language-yaml ext-yml line-numbers-mode"><pre v-pre class="language-yaml"><code><span class="token key atrule">spring</span><span class="token punctuation">:</span>
  <span class="token key atrule">messages</span><span class="token punctuation">:</span>
    <span class="token key atrule">basename</span><span class="token punctuation">:</span> i18n/Message
  <span class="token key atrule">freemarker</span><span class="token punctuation">:</span>
    <span class="token key atrule">suffix</span><span class="token punctuation">:</span> .html
  <span class="token key atrule">mvc</span><span class="token punctuation">:</span>
    <span class="token key atrule">throw-exception-if-no-handler-found</span><span class="token punctuation">:</span> <span class="token boolean important">true</span>
</code></pre><div class="line-numbers" aria-hidden="true"><span class="line-number">1</span><br><span class="line-number">2</span><br><span class="line-number">3</span><br><span class="line-number">4</span><br><span class="line-number">5</span><br><span class="line-number">6</span><br><span class="line-number">7</span><br></div></div><p>运行成功后，你将看到如下页面:</p>
<p><img src="/images/effect_index.png" alt="首页效果"></p>
<p>若您还有其他较为复杂的项目要求，请参照 <a href="https://gitee.com/cikaros/fly-feel-tests" target="_blank" rel="noopener noreferrer">项目样例<ExternalLinkIcon/></a> 。</p>
</template>
