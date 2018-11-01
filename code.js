/*Created by hzl-coding on 2018/10/11.*/
var orgList, currentnode , currentCodeID, isNewRecord, currentCode, currentCodeName, currentdictTypeMemo, currentnote,currentsort,validuserParam;
//var currentCodeID,currentCodeName,currentdictTypeMemo,currentnode,currentsort, orgList, isNewRecord, currentCode, validuserParam ,currentnode;
var editURL = "/views/sys/code/edit.html",editPURL = "/views/sys/code/pedit.html";

var modalOption = {
    url: "",
    selectorId: "codeForm",
    type: "add",
    validation: {},
    data:{},
    callback: ""
}

var setting = {
    data: {
        key: {name: 'name'},
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: 0
        }
    },
    callback: {
        onClick: myOnClick
    }
};

function myOnClick(event, treeId, treeNode) {
    currentnode = treeNode;
    currentCodeID = treeNode.id;
    currentCodeName = treeNode.name;
    currentdictTypeMemo=treeNode.dictTypeMemo;
    currentnote=treeNode.note;
    currentsort=treeNode.sort;
    gridOption.ext.refresh();
}

$("#btn_add").click(function () {
    isNewRecord = true;
    modalOption.type = "add";
    modalOption.url = editPURL;
    modalOption.callback = function(){
        //给弹出框的隐藏id输入框赋初始值操作
        $("#pId").val('0');
    }
    loadModalToTopWindow(modalOption);
});

$("#btn_edit").click(function () {
    isNewRecord = false;
    modalOption.url = editPURL;
    modalOption.type = "edit";
    modalOption.callback = "";
    var jsonstr='{"id":"' +currentnode.id + '","isNewRecord":false,"searchType":0,"isdel":0,"name":"' + currentnode.name + '","note":"' + currentnode.note + '","dictTypeMemo":"'+currentnode.dictTypeMemo +'","sort":"'+currentnode.sort+'","del":false}';
    currentCode = JSON.parse(jsonstr);          //json字符串转换成一个对象            json字符串构建一个code对象
    modalOption.data = JSON.parse(jsonstr);    //将转换好的对象放入到数据模型中
    loadModalToTopWindow(modalOption);

});
$("#btn_delete").click(function () {
    if (currentCodeName !== null || currentCodeName !== undefined || currentCodeName !== '') {
        $(parent).msg({
            type: 'confirm',
            cancelbtn: true,//是否显示取消按钮
            text: '确定删除' + currentnode.name + '？',   //是否删除提示信息
            sure: function () {     //点击删除按钮
                $.request("/a/sys/code/delete", {id: currentnode.id}, function (result) {//参数是某一条记录的ID
                    try {
                        if(result>0){
                            toastr.success('删除成功');
                            window.location.reload();
                        }else{
                            toastr.error('删除失败');
                        }
                        loadCodeTypeAll(); //刷新一下大表树
                    }
                    catch (ex) {
                        console.log(ex);  //显示后台返回的异常信息
                    }
                },function(error){
                    toastr.error('删除失败')
                });
            },
            cancel: function () { }     //点击取消按钮
        });
    }else {
        toastr.error('请选择码表大类树节点');    //没有选择大表的数据记录的提示信息
    }
});

var gridOption = {
    url: "/a/sys/code/findCodesByPId",
    placeholder: '名称',
    check: false,
    //显示分页，默认false
    ispage: true,
    SortBy:"sort",
    SortOrder:'asc',
    //显示序号 ，默认false
    no: true,
    //固定参数值，无论简单/高级搜索，都会将参数（pId）传到后端
    stableparm: function () {
        return {pId: currentCodeID};  //当前写法是符合json数据的格式
    },
    //简单查询参数
    keys: [ 'name'],
    //行事件
    events: {
        editCode: function (row) {
            isNewRecord = false;
            currentCode = row;
            //alert(JSON.stringify(row));
            modalOption.url = editURL;
            modalOption.type = "edit";
            modalOption.data = row;
            modalOption.callback = function(){
                //加载码表大类
                loadCodeTypedataAll(currentnode.id);
            }
            loadModalToTopWindow(modalOption);

        },
        deleteCode: function (row) {
            currentCode = row;
            $(parent).msg({
                type: 'confirm',
                cancelbtn: true,
                sure: function () {
                    $.request("/a/sys/code/delete", row, function (result) {
                        try {
                            if(result>0){
                                toastr.success('删除成功');
                            }else{
                                toastr.error('删除失败');
                            }
                            gridOption.ext.refresh();
                        }
                        catch (ex) {
                            console.log(ex)
                        }
                    },function(error){
                        toastr.error('删除失败');
                    });
                },
                cancel: function () { },
                text: '确定删除' + row.name + '？'
            });
        },
    },
    tools: [{
        text: '新增', img: 'fa fa-plus', event: function (selectItems) {
            isNewRecord = true;
            modalOption.type = "add";
            modalOption.url = editURL;
            modalOption.callback = function(){
                //赋值操作
                loadCodeTypedataAll(currentnode.id); //加载码表列表
            }
            loadModalToTopWindow(modalOption);

        }
    }]
};

function initModalOperation(){
    validataParam = {
        submitHandler: function (obj) {
            obj.isNewRecord = isNewRecord;
            if (!isNewRecord) {
                obj.id = currentCode.id;
            }
            $.request('/a/sys/code/saveCode', obj, function (result) {
                try {
                    if(result>0){
                        $('#codeData').modal("hide");
                        toastr.success("保存成功！");
                        gridOption.ext.refresh();
                    }else{
                        toastr.success("保存失败！");
                    }
                    loadCodeTypeAll();
                }
                catch (ex) {
                    //console.log(ex);
                }
            },function(error){
                toastr.error("保存失败！");
            }).always(function () {   //总是执行的代码
                if(obj.isNewRecord){
                    validataParam.resetBtn.trigger("reset");   //重置代码？？？？？？？？
                }
            });

        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '必填项'
                    },
                    Length: [200]
                }
            },
            dictTypeMemo: {
                validators: {
                    notEmpty: {
                        message: '必填项'
                    }
                }
            },
            sort: {
                validators: {
                    notEmpty: {
                        message: '必填项'
                    },
                    Length: [4],
                    dataType: {
                        dataType: 'decimal',
                        message: '只能输入数字'
                    }
                }
            }
        }
    };
}

function loadCodeTypeAll(){
    $.request("/a/sys/code/all",{token: $.cookie('jeesite_token')} ,function(data){
        var options = "<option id='0'>"+"--请选择--"+"</option>";
        for(var i =0;i<data.length;i++){
            options += "<option id='"+ data[i].id+"'>"+ data[i].name+"</option>";
        }
        orgList = data;
        var zTreeObj = $.fn.zTree.init($("#codeTypeTree"), setting , data);   //ID叫codeTypeTree的无序列表生成列表项
        var nodes = zTreeObj.getNodes();
        currentnode = nodes[0];
        currentCodeID = currentnode.id;
        currentdictTypeMemo = currentnode.dictTypeMemo;
        currentsort = currentnode.sort;
        currentnote = currentnode.note;
        zTreeObj.expandNode(currentnode, true, true, true);
    })
}

function loadCodeTypedataAll(id){    //生成小表编辑/新增模态框的下拉列表列表项
    $.request("/a/sys/code/all",{token: $.cookie('jeesite_token')},function(data){
        var options = "<option value='0'>" + "--请选择--" + "</option>";
        for (var i = 0; i < data.length; i++) {
            options += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
        }
        $("#pId").empty().append(options);    //清空下拉列表的元素
        $("#pId").val(id);
    })
}



//Set 当前页内容体对象
var BODY = $(this.document.body);

// load modal on code.html

function bindRemoveModalAction(){
    var $codeData = $("#codeData");
    $codeData.off('hidden.bs.modal');
    $codeData.on('hidden.bs.modal', function (e) {
        $codeData.empty();
        destorySelect2();
    })
}
function bindClickToSwitch(){
    var switchCheck = $(".checkbox-slider");
    _.each(switchCheck, function(item){
        $(item).prop("checked") == true ? $(item).val(1) :$(item).val(0);
    })
    switchCheck.on("click",function(event){
        $(event.target).prop("checked") == true ? $(event.target).val(1) : $(event.target).val(0);
    })
}
function destorySelect2(){
    $("[class*='select2']").remove();    //class名字包含select2的元素都移除
}
function loadModalToTopWindow(params){    //加载模态框
    var defaultCallback = function(){
        var selector = '#'+ opts.selectorId;
        if(opts.validation){
            $(selector,BODY).validate(opts.validation);
            opts.validation.resetBtn.trigger("reset");
        }
        if(opts.type === "edit"){
            $.setobject(opts.data, opts.selectorId, BODY);
        }
    };
    var modalPopFun = function(){
        $('#codeData').modal({backdrop:'static'});
        bindRemoveModalAction();
        bindClickToSwitch();
    };
    var opts = {
        url: "",
        selectorId: "",
        type: "add",
        validation: {},
        data:{},
        callback: ""
    };
    opts.url = params.url || "";
    opts.selectorId = params.selectorId || "";
    opts.type = _.isNull(params.type) || _.isUndefined(params.type) ?  "add" : params.type ;
    opts.validation = params.validation || {};
    opts.data = params.data || {};
    opts.callback = params.callback || function(){};
    $("#codeData").load(opts.url, function(){
        opts.callback();
        if(opts.type === "add" || opts.type === "edit"){
            defaultCallback();
        }
        modalPopFun();
    });
}

$(document).ready(function(){
    loadCodeTypeAll();
    $("#codeTypeData").table(gridOption);
    initModalOperation();
    modalOption.validation = validataParam;
})

