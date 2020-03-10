var App=function(){

    //iCheck
    var _masterCheckbox;
    var _checkbox;

    //用于存放ID 的数组
    var _idArray;

    /**
     * 私有方法，初始化ICheck
     */
    var handlerInitCheckedbox=function(){
        //激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        //获取控制端 Checkbox
        _masterCheckbox= $('input[type="checkbox"].minimal.icheck_master');

        //获取全部 Checkbox集合
         _checkbox=$('input[type="checkbox"].minimal');
    };

    /**
     * Checkbox 全选功能
     */
    var handlerCheckboxAll=function () {
        _masterCheckbox.on("ifClicked",function (e) {
            //返回true表示未选中
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }
            //选中状态
            else {
                _checkbox.iCheck("check");
            }
        });
    };

    /**
     * 批量删除
     */
    var handlerDeleteMulti=function (url) {
        _idArray=new Array();

        //将选中元素的ID放入数组中
        _checkbox.each(function () {
            var _id=$(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });
        //判断用户是否选择了数据项
        if (_idArray.length === 0) {
            $("#modal-message").html("亲! 好歹选择一个撒");
        }else {
            $("#modal-message").html("亲! 确定删除撒？");
        }
        //点击删除按钮时弹出模态框
        $("#modal-default").modal("show");
        //如果用户选择了数据项则调用删除方法
        $("#btnModalOk").bind("click",function () {
            del();
        });

        /**
         * 当前私有函数的私有函数，删除数据
         */
        function del() {

            $("#modal-default").modal("hide");

            //如果没有选择数据项的处理
            if (_idArray.length == 0) {
                //...
            }
            //删除操作
            else {
                setTimeout(function () {
                    $.ajax({
                        "url":url,
                        "type":"POST",
                        "data":{"ids":_idArray.toString()},
                        "dataType":"JSON",
                        "success":function (data) {
                            //请求成功后，无论是成功或是失败都需要弹出模态框进行提示，所以这里需要先解绑原来的click事件
                            $("#btnModalOk").unbind("click");
                            //请求成功(删除成功)
                            if (data.status == 200) {
                                //刷新页面
                                $("#btnModalOk").bind("click",function () {
                                    window.location.reload();
                                });
                            }
                            //请求失败(删除失败)
                            else {
                                //确定按钮的事件改为隐藏模态框
                                $("#btnModalOk").bind("click",function () {
                                    $("#modal-default").modal("hide");
                                });
                            }
                            //因为无论如何都需要提示信息，所以这里的模态框是必须调用的
                            $("#modal-message").html(data.message);
                            $("#modal-default").modal("show");
                        }
                    });
                },500);
            }
        }
    };
    /**
     * 初始化DataTables
     */
    var handlerInitDataTables=function (url,columns) {
        var _dataTable = $("#dataTable").DataTable({
            "paging":true,
            "info":true,
            "lengthChange":false,
            "ordering":false,
            "processing":true,
            "searching":false,
            "serverSide":true,
            "deferRender":true,
            "ajax":{
                "url":url
            },
            "columns":columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function (settings) {
                handlerInitCheckedbox();
                handlerCheckboxAll();
            }
        });
        return _dataTable;
    };

    /**
     * 初始化zTree
     * @param url
     * @param autoParm
     * @param callback
     */
    var handlerInitZTree=function (url,autoParm,callback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url:url,
                autoParam:autoParm
            }
        };

        $.fn.zTree.init($("#myTree"), setting);

        $("#btnModalOk").bind("click",function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes= zTree.getSelectedNodes();
            //未选择
            if (nodes.length == 0) {
                alert("请选择一个节点");
            }
            //已选择
            else{
                callback(nodes);
            }
        });
    };

    var handlerInitDropzone=function (elementId,url) {
        var myDropzone = new Dropzone("#d"+elementId, {
            url: url,
            paramName:"dropFile",
            maxFiles: 1,// 一次性上传的文件数量上限
            maxFilesize: 10, // 文件大小，单位：MB
            acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
            addRemoveLinks: true,
            parallelUploads: 1,// 一次上传的文件数量
            dictDefaultMessage: '拖动文件至此或者点击上传',
            dictMaxFilesExceeded: "您最多只能上传1个文件！",
            dictResponseError: '文件上传失败!',
            dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
            dictFallbackMessage: "浏览器不受支持",
            dictFileTooBig: "文件过大上传文件最大支持.",
            dictRemoveLinks: "删除",
            dictCancelUpload: "取消",
            init:function(){
                this.on("success",function(file,data){
                    $("#pic").val(data.fileName);
                });
            }
        });
    };


    /**
     * 查看详情
     * @param url
     */
    var handlerShowDetail=function (url) {
        //这里是通过 Ajax 请求 html 的方式将jsp 装载进模态框中
        $.ajax({
            url:url,
            type:"get",
            dataType:"html",
            success:function(data){
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };

    return {
        /**
         * 初始化
         */
        init:function () {
            //关闭 Dropzone 的自动发现功能
            Dropzone.autoDiscover=false;
            handlerInitCheckedbox();
            handlerCheckboxAll();
        },

        /**
         * 批量删除
         * @param url
         */
        deleteMulti:function (url) {
            handlerDeleteMulti(url);
        },

        /**
         * 初始化 DataTables
         * @param url
         * @param columns
         * @returns {jQuery}
         */
        initDataTables:function (url,columns) {
            return handlerInitDataTables(url, columns);
        },

        /**
         * 初始化zTree
         * @param url
         * @param autoParm
         * @param callback
         */
        initZTree:function(url,autoParm,callback){
            handlerInitZTree(url, autoParm, callback);
        },

        /**
         * 显示详情
         * @param url
         */
        showDetail:function(url){
            handlerShowDetail(url);
        }
    }
}();

$(document).ready(function () {
    App.init();
})