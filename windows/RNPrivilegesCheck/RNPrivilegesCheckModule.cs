using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Com.Reactlibrary.RNPrivilegesCheck
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNPrivilegesCheckModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNPrivilegesCheckModule"/>.
        /// </summary>
        internal RNPrivilegesCheckModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNPrivilegesCheck";
            }
        }
    }
}
