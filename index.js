
import { NativeModules } from 'react-native';

const  RNPrivilegesCheckModule = NativeModules.RNPrivilegesCheck;

export default class RNPrivilegesCheck{
    static async checkCamera(){
        try {
            let value = await RNPrivilegesCheckModule.checkCamera()
            return value
        }catch (err){
            throw err
        }
    }
    static async checkPhotos(){
        try {
            let value = await RNPrivilegesCheckModule.checkPhotos()
            return value
        }catch (err){
            throw err
        }
    }
    static getAppName(){
        return RNPrivilegesCheckModule.appName;
    }

};
