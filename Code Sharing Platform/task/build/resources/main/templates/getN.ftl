<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <meta charset="UTF-8">
    <title>Code</title>
</head>
<body>
    <span id = "load_date">${code.date}</span>
    <#if (code.views >= 0)>
        <p>
            <span id = "views_restriction">${code.views} more views allowed</span>
        </p>
    </#if>
    <#if (code.time > 0)>
    <p>
        <span id = "time_restriction">The code will be available for ${code.time} seconds</span>
    </p>
    </#if>
    <pre id = "code_snippet"><code>${code.code}</code></pre>
</body>
</html>