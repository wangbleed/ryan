<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript" src="${ctx}/resources/js/comet4j.js"></script>
    <script type="text/javascript">
        function init(){
            var kbDom = document.getElementById('kb');
            JS.Engine.on({
                ALARM_CHANNEL : function(kb){//侦听一个channel
                    kbDom.innerHTML = kb;
                }
            });
            JS.Engine.start('conn');
        }
    </script>
</head>
<body onload="init()">
	<h1>${message}</h1>
    <a href="user/list">Show User</a><br/>
    剩余内存：<span id="kb">...</span>KB
</body>
</html>