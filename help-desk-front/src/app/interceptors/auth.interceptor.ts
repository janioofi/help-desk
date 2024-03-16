import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  let token = localStorage.getItem('token');
  if(token){
    const cloneRed = req.clone({ headers: req.headers.set('Authorization', `Bearer ${token}`)})
    return next(cloneRed);
  }else{
    return next(req);
  }
};
