//服务层
app.service('producService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('../produc/findAll.do');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('../produc/findPage.do?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('../produc/findOne.do?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('../produc/add.do',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('../produc/update.do',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('../produc/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('../produc/search.do?page='+page+"&rows="+rows, searchEntity);
	}


    //上传文件
    this.uploadFile=function(){
        var formdata=new FormData();
        formdata.append('file',file.files[0]);//file 文件上传框的name

        return $http({
            url:'../upload.do',
            method:'post',
            data:formdata,
            headers:{ 'Content-Type':undefined },
            transformRequest: angular.identity
        });

    }
});
