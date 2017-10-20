
import { NativeModules } from 'react-native';

const  RNPrivilegesCheckModule = NativeModules.RNPrivilegesCheck;

export default class RNPrivilegesCheck{
    static async checkCamera(){
        return RNPrivilegesCheckModule.checkCamera
    }
    static async checkPhotos(){
        return RNPrivilegesCheckModule.checkPhotos
    }
};
