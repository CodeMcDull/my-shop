<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="../../static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css"/>
    <link rel="stylesheet" href="../../static/assets/plugins/dropzone/dropzone.css"/>
    <link rel="stylesheet" href="../../static/assets/plugins/dropzone/min/basic.min.css"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>



        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">

                    <c:if test="${baseResult!=null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <h4><i class="icon fa fa-ban"></i> Alert!</h4>
                                ${baseResult.message}
                        </div>
                    </c:if>

                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbContent.id==null? "新增":"编辑"}内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->

                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContent">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="categoryId" class="col-sm-2 control-label">父级类目</label>

                                    <div class="col-sm-10">
                                        <form:hidden path="categoryId"/>
                                        <input id="categoryName" class="form-control required" placeholder="请选择" readonly="true" data-toggle="modal" data-target="#modal-default">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label">标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="title" cssClass="form-control required" placeholder="标题"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-2 control-label">子标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="subTitle" cssClass="form-control required" placeholder="子标题"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>

                                    <div class="col-sm-10">
                                        <form:input path="titleDesc" cssClass="form-control required" placeholder="标题描述"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">链接</label>

                                    <div class="col-sm-10">
                                        <form:input path="url" cssClass="form-control required" placeholder="链接"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">图片1</label>
                                    <div class="col-sm-10">
                                        <form:input path="pic" cssClass="form-control required" placeholder="图片1"/>
                                        <div id="dropz" class="dropzone"></div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">图片2</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic2" cssClass="form-control required" placeholder="图片2"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">详情</label>

                                    <div class="col-sm-10">
                                        <form:textarea rows="5" path="content" class="form-control required" placeholder="请输入" />
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>

                    </div>
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="../includes/copyright.jsp"/>
</div>

<jsp:include page="../includes/footer.jsp"/>
<script src="../../static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="../../static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<%--自定义模态框--%>
<sys:modal title="请选择" message="<ul id='myTree' class='ztree'></ul>"/>

<script>
    $(function () {
        App.initZTree("/content/category/tree/data",["id"],function (nodes) {
            var node=nodes[0];
            $("#categoryId").val(node.id);
            $("#categoryName").val(node.name);
            $("#modal-default").modal("hide");
        });
    });

    // Dropzone.options.dropz={
    //     url: "/upload",
    //     paramName:"dropFile",
    //     dictDefaultMessage: '拖动文件至此或者点击上传', // 设置默认的提示语句
    //     init:function(){
    //         this.on("success",function(file,data){
    //             $("#pic").val(data.fileName);
    //         });
    //     }
    // };
    Dropzone.autoDiscover=false;
    var myDropzone = new Dropzone("#dropz", {
        url: "/upload",
        paramName:"dropFile",
        dictDefaultMessage: '拖动文件至此或者点击上传', // 设置默认的提示语句
        init:function(){
            this.on("success",function(file,data){
                $("#pic").val(data.fileName);
            });
        }
    });
</script>
</body>
</html>