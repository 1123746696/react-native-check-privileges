
#import "RNPrivilegesCheck.h"

@implementation RNPrivilegesCheck

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(checkCamera:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject){
    [self checkCameraPermissions:^(BOOL granted) {
        if(granted){
            resolve(@1);
        }else{
            reject(@0,@"没有获取权限",nil);
        }
    }];
}

RCT_EXPORT_METHOD(checkPhotos:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject){
    [self checkPhotosPermissions:^(BOOL granted) {
        if(granted){
            resolve(@1);
        }else{
            reject(@0,@"没有获取权限",nil);
        }
    }];
}
- (void)checkCameraPermissions:(void(^)(BOOL granted))callback
{
    AVAuthorizationStatus status = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];
    if (status == AVAuthorizationStatusAuthorized) {
        callback(YES);
        return;
    } else if (status == AVAuthorizationStatusNotDetermined){
        [AVCaptureDevice requestAccessForMediaType:AVMediaTypeVideo completionHandler:^(BOOL granted) {
            callback(granted);
            return;
        }];
    } else {
        callback(NO);
    }
}

- (void)checkPhotosPermissions:(void(^)(BOOL granted))callback
{
    PHAuthorizationStatus status = [PHPhotoLibrary authorizationStatus];
    if (status == PHAuthorizationStatusAuthorized) {
        callback(YES);
        return;
    } else if (status == PHAuthorizationStatusNotDetermined) {
        [PHPhotoLibrary requestAuthorization:^(PHAuthorizationStatus status) {
            if (status == PHAuthorizationStatusAuthorized) {
                callback(YES);
                return;
            }
            else {
                callback(NO);
                return;
            }
        }];
    }
    else {
        callback(NO);
    }
}
- (NSDictionary *)constantsToExport
{
    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
    return @{
             @"appName": [infoDictionary objectForKey:@"CFBundleDisplayName"],
             };
}
@end
  
